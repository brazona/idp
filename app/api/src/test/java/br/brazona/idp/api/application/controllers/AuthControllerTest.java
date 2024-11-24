package br.brazona.idp.api.application.controllers;

import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
public class AuthControllerTest {
    private static final String USERNAME = "cezar.silva@gmail.com";
    private static final String PASSWORD = "admin";
    private static final String PASSWORD_FAKE = "admin1";
    private static final String USERNAME_FAKE = "cezar1.silva@gmail.com";
    private static final String URI = "/api/v1/auth/authentication";
    private static final String JSON = "{\n" +
            "    \"username\": \"{{username}}\",\n" +
            "    \"password\": \"{{password}}\"\n" +
            "}";
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Authentication User Success")
    public void should_authentication_success() {
        AuthRequestBusinessVO user = new AuthRequestBusinessVO(USERNAME, PASSWORD);
        Gson json = new Gson();
        Response response =
                given()
                        .contentType("application/json")
                        .body(json.toJson(user))
//                        .body(JSON)
                        .when()
                        .post(URI)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        String contentType = response.header("Content-Type");
        System.out.println("Content-Type: " + contentType);
    }
}