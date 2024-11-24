package br.brazona.idp.api.domain.services.business;

import br.brazona.idp.api.domain.dto.AuthDTO;
import br.brazona.idp.api.domain.dto.UserDTO;
import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import br.brazona.idp.api.domain.views.business.AuthResponseBusinessVO;
import br.brazona.idp.api.domain.views.business.UserDetailsVO;
import br.brazona.idp.api.infrastructure.entities.UsersEntity;
import br.brazona.idp.api.infrastructure.repositories.UsersRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static br.brazona.idp.api.domain.constants.UnitTestConst.*;
import static org.mockito.BDDMockito.given;
@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
@SpringBootTest
@Disabled
class AuthServiceTest {

    @InjectMocks
    AuthService authService;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private UserService userService;
    @Mock
    private UserDTO userDTO;
    @Mock
    private UserDetailsVO userDetailsVO;
    @Mock
    private UserDetails userDetails;
    @Mock
    private AuthRequestBusinessVO authRequestBusinessVO;
    @Mock
    private AuthResponseBusinessVO authResponseBusinessVO;
    @Mock
    private AuthDTO authDTO;
    private UsersEntity usersEntity;
    @BeforeEach
    public void setup(){
        usersEntity = new UsersEntity("Test",USERNAME_TEST,"$2a$10$0aFkQ9xFY.tLmmnymiY0puLAGFkin3n0MDMfqJWJ//XaskkpLmOF."
                ,true,true,true,true);
        authRequestBusinessVO = new AuthRequestBusinessVO(USERNAME_TEST,PASSWORD);
    }

    @Test
    @DisplayName("Authentication User Success")
    void should_authentication_success() {
        // precondition
        UsersEntity usersEntity1 = usersRepository.save(usersEntity);
        given(usersRepository.findByUsername(authRequestBusinessVO.getUsername())).willReturn(usersEntity1);
        AuthResponseBusinessVO responseBusinessVO = authService.authentication(authRequestBusinessVO);
        boolean valid = responseBusinessVO != null && responseBusinessVO.getToken().isEmpty();
        Assertions.assertTrue(valid);
    }
    @Test
    @DisplayName("Authentication User Not Found")
    public void should_not_authentication_not_found() {
        AuthRequestBusinessVO user = new AuthRequestBusinessVO(USERNAME_FAKE, PASSWORD);
        try{
           AuthResponseBusinessVO token = authService.authentication(user);
            System.out.println(token);
        }catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }

//        assertThrows(UserNotFoundException.class, () -> );

    }
    @Test
    @DisplayName("Authentication User Forbidden")
    public void should_not_authentication_forbidden() {
        AuthRequestBusinessVO user = new AuthRequestBusinessVO(USERNAME, PASSWORD_FAKE);

    }
    @Test
    @DisplayName("Authentication Not Authorized")
    public void should_not_authorized() {
        AuthRequestBusinessVO user = new AuthRequestBusinessVO(USERNAME, PASSWORD);

    }

    @Test
    void loadUserByUsername() {
    }
}