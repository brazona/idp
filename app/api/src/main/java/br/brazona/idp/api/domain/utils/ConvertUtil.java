package br.brazona.idp.api.domain.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;
@Component
public class ConvertUtil {

    Base64.Encoder enc = Base64.getEncoder();
    Base64.Decoder dec = Base64.getDecoder();

    public String stringToBase64(String value){
        return Base64.getEncoder().encodeToString(value.getBytes());
    }
    public String bse64ToString(String value){
        byte[] decodedBytes = Base64.getDecoder().decode(value);
        return new String(decodedBytes);
    }
}
