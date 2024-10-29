package br.brazona.idp.api.services.business;

import br.brazona.idp.api.core.dtos.business.*;
import br.brazona.idp.api.core.dtos.keycloak.IntrospectResponseDTO;
import br.brazona.idp.api.core.dtos.keycloak.TokenResponseDTO;
import br.brazona.idp.api.core.exception.UnavailableServicedException;
import br.brazona.idp.api.core.utils.AuthUtil;
import br.brazona.idp.api.core.utils.JwtUtils;
import br.brazona.idp.api.services.keycloak.IAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class AuthService {

    @Autowired
    private IAuthService oauth2Service;

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    private final static String SERVICE_LOG = "Service started AuthService: {}";
    Logger logger = LoggerFactory.getLogger(AuthService.class);

    public Boolean authorization(Long user_id){
        logger.info(SERVICE_LOG, "authorization");

        ObjectMapper objectMapper = new ObjectMapper();
        SessionDTO sessionDTO = sessionService.getByUserId(user_id);

        if (sessionDTO != null){

            logger.info("Session active");

            Map<String, Object> obj =
                    objectMapper.convertValue(
                            authUtil.isValidTokenRequest(sessionDTO.getAccess_token())
                            , Map.class);
            ResponseEntity<IntrospectResponseDTO> resp = oauth2Service.authorizationIntrospect(obj);


            if (resp == null || resp.getStatusCode().value() != 200 || resp.getBody() == null) {
               throw new UnavailableServicedException();
            }

            IntrospectResponseDTO introspectResponseDTO = resp.getBody();
            assert introspectResponseDTO != null;
            return introspectResponseDTO.isActive();

        }
        return false;

    }

    public TokenDTO signIn (UserDTO user){

        logger.info(SERVICE_LOG, "signIn");
        ObjectMapper objectMapper = new ObjectMapper();

        UserDetailsImplDTO userDetails =  userDetailsService.loadUserByUsername(user.getUsername());
        logger.info("Identified user");
        Map<String, Object> map =
                objectMapper.convertValue(
                        authUtil.request(new LoginRequestDTO(
                                userDetails.getName(),
                                user.getPassword()))
                        , Map.class);
        ResponseEntity<TokenResponseDTO> resp = oauth2Service.signIn(map);

        if (resp == null || resp.getStatusCode().value() != 200 || resp.getBody() == null) {
            logger.error("Unavailable service: {}", "Keycloak");
            throw new UnavailableServicedException();
        }

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        Authentication authentication = authenticateAgainstThirdPartyAndGetAuthentication(
                userDetails.getUsername(), userDetails.getPassword(), grantedAuths
        );
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        assert resp != null;
        TokenResponseDTO tokenResponseDTO = resp.getBody();

        assert tokenResponseDTO != null;
        SessionDTO sessionDTO = new SessionDTO(userDetails.getId(), jwt, tokenResponseDTO.getAccess_token(),
                tokenResponseDTO.getExpires_in(), tokenResponseDTO.getRefresh_expires_in(), tokenResponseDTO.getRefresh_token());

        sessionService.save(sessionDTO);
        logger.info("Token generated");
        return new TokenDTO(jwt);

    }

    private static UsernamePasswordAuthenticationToken authenticateAgainstThirdPartyAndGetAuthentication(String name, String password, List<GrantedAuthority> grantedAuths) {
        final UserDetails principal = new User(name, password, grantedAuths);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }
}