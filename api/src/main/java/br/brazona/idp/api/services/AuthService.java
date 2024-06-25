package br.brazona.idp.api.services;

import br.brazona.idp.api.core.dtos.*;
import br.brazona.idp.api.core.exception.AccessDeniedException;
import br.brazona.idp.api.core.models.LoginKeycloakResponseModel;
import br.brazona.idp.api.core.models.LoginRequestModel;
import br.brazona.idp.api.core.models.LoginResponseModel;
import br.brazona.idp.api.core.utils.AuthUtil;
import br.brazona.idp.api.core.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private AuthDTO authDTO;
    @Autowired(required = false)
    private IAuthService oauth2Service;
    @Autowired
    private UserService userService;
    //    private UsersRepository usersRepository;
    @Autowired
    private SessionUtil sessionUtil;
    @Autowired
    private AuthUtil authUtil;

    public TokenDTO getToken(UserDTO userDTO) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();

        userService.isPresentByName(userDTO.getUsername());
        Long id = userService.getIdByname(userDTO.getUsername());
        SessionDTO session = sessionUtil.getSession(id.toString());
        if (session != null && session.getHash() != null) {
            return new TokenDTO(session.getHash());
        }

        Map<String, Object> map =
                objectMapper.convertValue(
                        authUtil.request(new LoginRequestModel(
                                userDTO.getUsername(),
                                userDTO.getPassword()))
                        , Map.class);
        ResponseEntity<String> resp = oauth2Service.signIn(map);
        if (resp.getStatusCode().value() != 200) {
            return new TokenDTO(resp.getBody());
        }
        LoginResponseModel response = authUtil.response(
                gson.fromJson(resp.getBody(), LoginKeycloakResponseModel.class));

        String token = authUtil.generateHash(14);
        Authentication authentication = authenticateAgainstThirdPartyAndGetAuthentication("user1", "user1");

        sessionUtil.setSession(new SessionDTO(
                id,
                response.getResult(),
                token
        ));
        return new TokenDTO(token);

    }

    public boolean isTokenValidated(String token, String userId) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();

        userService.isPresentById(Long.parseLong(userId));
        SessionDTO session = sessionUtil.getSession(userId);

        if (session.getHash().isEmpty() || !session.getHash().equalsIgnoreCase(token)) {
            throw new AccessDeniedException();
        }
        Map<String, Object> map =
                objectMapper.convertValue(
                        authUtil.isValidTokenRequest(session.getToken_access())
                        , Map.class);
        ResponseEntity<String> resp = oauth2Service.validatedToken(map);

        KeycloakIntrospectResponseDTO keycloakIntrospectResponseDTO
                = gson.fromJson(resp.getBody(), KeycloakIntrospectResponseDTO.class);
        if (!keycloakIntrospectResponseDTO.isActive()) {
            throw new AccessDeniedException();
        }
        return true;
    }
    public String getAccessToken(String userId) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();

        userService.isPresentById(Long.parseLong(userId));
        SessionDTO session = sessionUtil.getSession(userId);

        if (session == null || session.getToken_access().isEmpty()) {
            throw new AccessDeniedException();
        }
        return session.getToken_access();
    }
    private static UsernamePasswordAuthenticationToken authenticateAgainstThirdPartyAndGetAuthentication(String name, String password) {
        final List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
        grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        final UserDetails principal = new User(name, password, grantedAuths);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }
}