package br.brazona.idp.api.core.config;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ConstantsTest {

    public final static String  USER_NAME = "user1@brazona.com.br";
    public final static String  USER_NAME_FAKE = "user1@not.com.br";
    public final static Long  USER_ID_FAKE = 101010L;

    public final static String  USER_PASS = "user1";
    public final static String  USER_PASS_FAKE = "user1111";
    public final static String  URI_AUTH = "/api/v1/auth/signin";
    public static final org.springframework.http.MediaType APPLICATION_JSON_UTF8 = new org.springframework.http.MediaType(org.springframework.http.MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
}
