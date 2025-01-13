package br.brazona.idp.api.domain.utils;

import br.brazona.idp.api.domain.views.business.AbstractVO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

/**
* 
* Class that transforms authentication data.
* 
* @author Brazona Tech
* @version 1.0
* @since 1.0
*
**/
@Component
public class RandomPasswordGeneratorUtil {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+{}[]";

    private static final String ALL_CHARS = UPPER + LOWER + DIGITS + SPECIAL_CHARS;

    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomPassword(int length) {
        StringBuilder password = new StringBuilder(length);

        // At least one uppercase letter
        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        // At least one lowercase letter
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        // At least one digit
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        // At least one special character
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // Remaining characters randomly selected from all characters
        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        // Shuffle the characters in the password
        return shuffleString(password.toString());
    }

    private static String shuffleString(String input) {
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            int randomIndex = random.nextInt(charArray.length);
            char temp = charArray[i];
            charArray[i] = charArray[randomIndex];
            charArray[randomIndex] = temp;
        }
        return new String(charArray);
    }

    public static void main(String[] args) {
        // Generate a random password with a length of 12 characters
        String randomPassword = generateRandomPassword(12);
        System.out.println("Random Password: " + randomPassword);
    }
}
