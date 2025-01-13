package br.brazona.idp.api.domain.utils;

import br.brazona.idp.api.domain.views.business.AbstractVO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class ConvertUtil {
    @Autowired
    private Gson json;
    Base64.Encoder enc = Base64.getEncoder();
    Base64.Decoder dec = Base64.getDecoder();
    /**
     * Method Constructor class.
     *
     **/
    public ConvertUtil() {
    }

    /**
     * Method that converts raw text into base64 text.
     *
     * @param value text with raw value.
     * @return String, with text in base64
     * 
     **/
    public String stringToBase64(String value){
        return Base64.getEncoder().encodeToString(value.getBytes());
    }
    /**
     * Method that converts base64 text into raw text.
     *
     * @param value text with value in base64 format.
     * @return String, with text in raw
     * 
     **/
    public String base64ToString(String value){
        byte[] decodedBytes = Base64.getDecoder().decode(value);
        return new String(decodedBytes);
    }
    /**
     * 
     * Method that provides the object with authentication data.
     * 
     * @param obj Object of the User class, with the application's authentication values.
     * @return String, with text in format json
     *
     **/
    public String objToJson(AbstractVO obj) {
        return json.toJson(obj);
    }
}
