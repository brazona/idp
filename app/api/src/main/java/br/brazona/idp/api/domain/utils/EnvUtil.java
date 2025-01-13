package br.brazona.idp.api.domain.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
public class EnvUtil {

    @Autowired
    private Environment environment;

    /**
     *
     * Method that provides the value of an application property.
     *
     **/
    public EnvUtil() {
    }

    /**
     *
     * Method that provides the value of an application property.
     *
     * @param key property identifier value.
     * @return a variable value
     * 
     **/
    public String getEnvValue(String key) {
        return environment.getProperty(key);
    }
}
