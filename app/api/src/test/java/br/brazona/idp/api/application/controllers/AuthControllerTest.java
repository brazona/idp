package br.brazona.idp.api.application.controllers;

import br.brazona.idp.api.domain.views.business.AuthRequestBusinessVO;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static br.brazona.idp.api.domain.constants.UnitTestConst.*;
import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

    @LocalServerPort
    private int port;
    private static final BCryptPasswordEncoder passwordEcorder = new BCryptPasswordEncoder();
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