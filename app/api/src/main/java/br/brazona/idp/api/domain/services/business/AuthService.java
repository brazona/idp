package br.brazona.idp.api.domain.services.business;


import br.brazona.idp.api.domain.exceptions.AccessDeniedException;
import br.brazona.idp.api.domain.exceptions.ResourceNotFoundException;
import br.brazona.idp.api.domain.exceptions.UnavailableServicedException;
import br.brazona.idp.api.domain.services.keycloak.IAuthService;
import br.brazona.idp.api.domain.utils.JwtUtils;
import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.AuthResponseBusinessVO;
import br.brazona.idp.api.domain.views.keycloak.AuthResponseKeycloakVO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.brazona.idp.api.domain.constants.ExceptionConst.*;

@Slf4j
@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private IAuthService authServiceKeycloak;

    @Autowired
    private SessionService sessionService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UserService userService;
    Logger logger = LoggerFactory.getLogger(AuthService.class);

    public Boolean authorization(String user_id) {

        return false;

    }

    public AuthResponseBusinessVO authentication(AuthRequestBusinessVO user) {


        return null;

    }

    private static UsernamePasswordAuthenticationToken authenticateAgainstThirdPartyAndGetAuthentication(String name, String password, List<GrantedAuthority> grantedAuths) {
        final UserDetails principal = new User(name, password, grantedAuths);
        return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
    }

    private void responseValidator(ResponseEntity<AuthResponseKeycloakVO> response) {
        Gson gson = new Gson();
        log.info(SERVICE_LOG_INFO, "keycloak", "Validate Response");
        if (response == null || response.getStatusCode().value() != 200) {
            log.error(UNAVAILABLE_SERVICE_ERROR);
            log.info(UNAVAILABLE_SERVICE_INFO, "keycloak");
            log.debug(UNAVAILABLE_SERVICE_DEBUG, "keycloak", "no response");
            throw new UnavailableServicedException();
        }
        if (response.getStatusCode().value() != 200) {
            log.debug(UNAVAILABLE_SERVICE_DEBUG, "keycloak", gson.toJson(response));
        }
        if (response.getStatusCode().value() == 503) {
            throw new AccessDeniedException();
        } else if (response.getStatusCode().value() == 404) {
            throw new ResourceNotFoundException(NOT_FOUND);
        }
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}