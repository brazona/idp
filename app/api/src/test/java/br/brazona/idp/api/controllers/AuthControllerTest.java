package br.brazona.idp.api.controllers;

import br.brazona.idp.api.IdpApplication;
import br.brazona.idp.api.core.config.ConstantsTest;
import br.brazona.idp.api.core.config.ContainersConfigTest;
import br.brazona.idp.api.core.dtos.business.TokenDTO;
import br.brazona.idp.api.core.dtos.business.UserDTO;
import br.brazona.idp.api.services.business.AuthService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(classes = IdpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
@AutoConfigureMockMvc
public class AuthControllerTest extends ContainersConfigTest {

    private String access_token;
    @Autowired
    private MockMvc mockMvc;
    @LocalServerPort
    private Integer port;

    @Autowired
    AuthService authService;

    @MockBean
    AuthController authController;

    @BeforeEach
    void setUp() {
        getToken();
    }

    @Test
    @DisplayName("Should Sign In Success")
    void should_sign_in_success() throws Exception {
        Gson json = new Gson();
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/auth/signin").contentType(ConstantsTest.APPLICATION_JSON_UTF8)
                        .content(json.toJson(new UserDTO(ConstantsTest.USER_NAME, ConstantsTest.USER_PASS))))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
    @Test
    @DisplayName("Should Sign In Access Denied")
    void should_sign_in_access_denied() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/assets/v1/all"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

    }
    @Test
    @DisplayName("Should Sign In User Not Found")
    void should_sign_in_user_not_found() throws Exception {

        Gson json = new Gson();
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/auth/signin").contentType(ConstantsTest.APPLICATION_JSON_UTF8)
                        .content(json.toJson(new UserDTO(ConstantsTest.USER_NAME_FAKE, ConstantsTest.USER_PASS))))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    @Test
    @DisplayName("Should Sign in Not Authorize")
    void should_sign_in_user_not_authorize() throws Exception {

        Gson json = new Gson();
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/auth/signin").contentType(ConstantsTest.APPLICATION_JSON_UTF8)
                        .content(json.toJson(new UserDTO(ConstantsTest.USER_NAME, ConstantsTest.USER_PASS_FAKE))))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @DisplayName("Should Authorization")
    void should_authorization() throws Exception {
        String token = "Bearer " +
                this.access_token;
        
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/auth/authorization")
                .contentType(ConstantsTest.APPLICATION_JSON_UTF8)
//                        .content(json.toJson(new UserDTO(ConstantsTest.USER_NAME, ConstantsTest.USER_PASS_FAKE))))
                .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @DisplayName("Should Not Authorization")
    void should_not_authorization() throws Exception {

        String token = "Bearer " +
                "FAKE";

            mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/auth/authorization")
                        .contentType(ConstantsTest.APPLICATION_JSON_UTF8)
//                        .content(json.toJson(new UserDTO(ConstantsTest.USER_NAME, ConstantsTest.USER_PASS_FAKE))))
                        .header("Authorization", token))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    private void getToken (){
        TokenDTO tokenDTO = authService.signIn(new UserDTO(ConstantsTest.USER_NAME, ConstantsTest.USER_PASS));
        this.access_token = tokenDTO.getToken();
    }

}
