package br.brazona.idp.api.services;

import br.brazona.idp.api.core.config.security.JwtUtils;
import br.brazona.idp.api.core.dtos.*;
import br.brazona.idp.api.core.exception.AccessDeniedException;
import br.brazona.idp.api.core.models.LoginKeycloakResponseModel;
import br.brazona.idp.api.core.models.LoginRequestModel;
import br.brazona.idp.api.core.models.LoginResponseModel;
import br.brazona.idp.api.core.utils.AuthUtil;
import br.brazona.idp.api.core.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@ComponentScan( basePackages = {"org.springframework.security.authentication.*"})
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

    @Autowired(required = false)
    AuthenticationManager authenticationManager;

    @Autowired(required = false)
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

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

    public TokenDTO signing (UserDTO singinRequestDTO){
        UserDTO userDTO = userService.getByname(singinRequestDTO.getUsername());
        UserDetails user = userService.loadUserByUsername(singinRequestDTO.getUsername());

        Authentication auth = authenticationManager.authenticate(authenticateAgainstThirdPartyAndGetAuthentication(user.getUsername(), user.getUsername(), (List<GrantedAuthority>) user.getAuthorities()));
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        String token1 = jwtUtils.generateJwtToken(authentication);
        String token2 = jwtUtils.generateJwtToken(auth);

//        SecurityContextHolder.getContext()
//                .setAuthentication(authentication);
////        UserDetailsDTO userDetails = (UserDetailsDTO) authentication.getPrincipal();

        return new TokenDTO(token1);
    }

    private static UsernamePasswordAuthenticationToken authenticateAgainstThirdPartyAndGetAuthentication(String name, String password, List<GrantedAuthority> grantedAuths) {
        final UserDetails principal = new User(name, password, grantedAuths);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }
}