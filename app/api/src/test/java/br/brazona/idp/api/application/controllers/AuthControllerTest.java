package br.brazona.idp.api.application.controllers;

import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {
    private static final String USERNAME = "cezar.silva@gmail.com";
    private static final String PASSWORD = "admin";
    private static final String PASSWORD_FAKE = "admin1";
    private static final String USERNAME_FAKE = "cezar1.silva@gmail.com";
    private static final String URI = "/api/v1/auth/authentication";

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Authentication User Success")
    public void should_authentication_success() {
        AuthRequestBusinessVO user = new AuthRequestBusinessVO(USERNAME, PASSWORD);
        Gson json = new Gson();
                given()
                        .contentType("application/json")
                        .body(json.toJson(user))
                        .when()
                        .post(URI)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
    }
    @Test
    @DisplayName("Authentication User Not Found")
    public void should_not_authentication_not_found() {
        AuthRequestBusinessVO user = new AuthRequestBusinessVO(USERNAME_FAKE, PASSWORD);
        Gson json = new Gson();
            given()
                    .contentType("application/json")
                    .body(json.toJson(user))
                    .when()
                    .post(URI)
                    .then()
                    .statusCode(404)
                    .extract()
                    .response();
    }
    @Test
    @DisplayName("Authentication User Forbidden")
    public void should_not_authentication_forbidden() {
        AuthRequestBusinessVO user = new AuthRequestBusinessVO(USERNAME, PASSWORD_FAKE);
        Gson json = new Gson();
        given()
                .contentType("application/json")
                .body(json.toJson(user))
                .when()
                .post(URI)
                .then()
                .statusCode(403)
                .extract()
                .response();
    }
    @Test
    @DisplayName("Authentication Not Authorized")
    public void should_not_authorized() {
        AuthRequestBusinessVO user = new AuthRequestBusinessVO(USERNAME, PASSWORD);
        Gson json = new Gson();
        given()
                .contentType("application/json")
                .body(json.toJson(user))
                .when()
                .post(URI.replace("/api/", "/app/"))
                .then()
                .statusCode(404)
                .extract()
                .response();
    }
}