package br.brazona.idp.api.services.business;

import br.brazona.idp.api.IdpApplication;
import br.brazona.idp.api.core.config.ConstantsTest;
import br.brazona.idp.api.core.config.ContainersConfigTest;
import br.brazona.idp.api.core.dtos.business.TokenDTO;
import br.brazona.idp.api.core.dtos.business.UserDTO;
import br.brazona.idp.api.core.dtos.business.UserDetailsImplDTO;
import br.brazona.idp.api.core.utils.JwtUtils;
import feign.FeignException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest(classes = IdpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
class AuthServiceTest extends ContainersConfigTest {

    @Autowired
    AuthService authService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @LocalServerPort
    private Integer port;
    @Autowired
    private JwtUtils jwtUtils;

    @Test
    @DisplayName("Should Sign In Success")
    void should_sign_in_success() {
//        TokenDTO tokenDTO = authService.signIn(new UserDTO(ConstantsTest.USER_NAME, ConstantsTest.USER_PASS));
        Assertions.assertFalse(getToken().getToken().isEmpty());
    }
    @Test
    @DisplayName("Should Sign In User Not Found")
    void should_sign_in_user_not_found() {
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            authService.signIn(new UserDTO(ConstantsTest.USER_NAME_FAKE, ConstantsTest.USER_PASS));
        });
        Assertions.assertEquals(UsernameNotFoundException.class, throwable.getClass());

    }
    @Test
    @DisplayName("Should Sign in Not Authorize")
    void should_sign_in_user_not_authorize() {
        Throwable throwable =  assertThrows(Throwable.class, () -> {
            authService.signIn(new UserDTO(ConstantsTest.USER_NAME, ConstantsTest.USER_PASS_FAKE));
        });

        Assertions.assertEquals(FeignException.FeignClientException.Unauthorized.class, throwable.getClass());
    }

    @Test
    @DisplayName("Should Authorization")
    void should_authorization() {

        String username = jwtUtils.getUserNameFromJwtToken(getToken().getToken());
        UserDetailsImplDTO userDetails = userDetailsService.loadUserByUsername(username);
        Assertions.assertTrue(authService.authorization(userDetails.getId()));

    }

    @Test
    @DisplayName("Should Not Authorization")
    void should_not_authorization() {
        Assertions.assertFalse(authService.authorization(ConstantsTest.USER_ID_FAKE));
    }
    private TokenDTO getToken (){
        return authService.signIn(new UserDTO(ConstantsTest.USER_NAME, ConstantsTest.USER_PASS));
    }
}