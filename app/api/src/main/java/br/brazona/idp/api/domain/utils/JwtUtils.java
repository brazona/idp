package br.brazona.idp.api.domain.utils;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
* 
* Class that transforms authentication data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration_ms}")
    private int jwtExpirationMs;
    private static BCryptPasswordEncoder passwordEcorder = new BCryptPasswordEncoder();

    /**
     *
     * Method constructor.
     *
     **/
    public JwtUtils() {
    }

    /**
     * Method that generates a token from authentication.
     *
     * @param authentication Authenticator class.
     * @return String, with token
     * 
     **/
    public String generateJwtToken(Authentication authentication) {

        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .subject((userPrincipal.getUsername()))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSigningKey())
                .compact();

    }
    private SecretKey getSigningKey() {
        byte[] keyBytes = this.jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    /**
     * Method that retrieves the user of a given token.
     *
     * @param token jwt token.
     * @return String, username
     * 
     **/
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();

    }
    /**
     * Method that validates access token.
     *
     * @param authToken jwt token.
     * @return boolean, true to valid token or false to invalid.
     * @throws SignatureException Error identifying token signature.
     * @throws MalformedJwtException Error in token format.
     * @throws ExpiredJwtException Token time expired.
     * @throws ExpiredJwtException Token time expired..
     * 
     * 
     **/
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.info("Badly formatted jwt: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("expired token: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("unsupported format: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.info("illegal argument: {}", e.getMessage());
        }

        return false;
    }
    /**
     * Method that generates encrypted text.
     *
     * @param plainText raw text.
     * @return String, with token
     * 
     **/
    public String bcryptEncryptor(String plainText) {
        return passwordEcorder.encode(plainText);
    }

    /**
     * method that compares two passwords.
     *
     * @param rawPassword raw text password.
     * @param encodedPassword encrypted text password.
     * @return Boolean, true to valid password or false to not valid.
     *
     **/
    public Boolean doPasswordsMatch(String rawPassword, String encodedPassword) {
        return passwordEcorder.matches(rawPassword, encodedPassword);
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param rawPassword Object of the User class, with the application's authentication values.
     * @param encodedPassword Object of the User class, with the application's authentication values.
     * @return Boolean, true to valid password or false to not valid.
     *
     **/
    public Boolean passwordsMatch(String rawPassword, String encodedPassword) {
        return rawPassword.equalsIgnoreCase(encodedPassword);
    }
}
