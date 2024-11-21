package br.brazona.idp.api.domain.utils;

import org.springframework.stereotype.Component;

@Component
public class ExceptionUtil {
    private static final String REPLACE_KEY = "{}";

    public String replaceKey(String message, String value) {
        return message.replace(REPLACE_KEY, value);
    }
}
