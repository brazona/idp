package br.brazona.idp.api.domain.utils;

import org.springframework.stereotype.Component;

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
public class ExceptionUtil {
    private static final String REPLACE_KEY = "{}";
    /**
     *
     * Class constructor.
     *
     **/
    public ExceptionUtil() {
    }
    /**
     * Method that replaces an identifying character in a text with a word.
     *
     * @param message Text with the identifier to be replaced.
     * @param value Value that will replace the text identifier character.
     * @return String, with the replaced value.
     * 
     **/
    public String replaceKey(String message, String value) {
        return message.replace(REPLACE_KEY, value);
    }
}
