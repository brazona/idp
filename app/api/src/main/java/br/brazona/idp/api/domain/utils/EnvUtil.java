package br.brazona.idp.api.domain.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvUtil {

    @Autowired
    private Environment environment;

    public String getEnvValue(String key) {
        return environment.getProperty(key);
    }
}
