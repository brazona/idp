package br.brazona.idp.api.services;

import br.brazona.idp.api.core.dtos.Oauth2DTO;
import br.brazona.idp.api.core.models.LoginKeycloakResponseModel;
import br.brazona.idp.api.core.models.LoginRequestModel;
import br.brazona.idp.api.core.models.LoginResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Oauth2Service {
    @Autowired
    private Oauth2DTO oauth2DTO;
    @Autowired(required = false)
    private IOauth2Service oauth2Service;


    public LoginResponseModel login (LoginRequestModel login){
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();

        Map<String, Object> map = objectMapper.convertValue(oauth2DTO.request(login)
                , Map.class);
        return oauth2DTO.response(
                gson.fromJson(oauth2Service.signIn(map).getBody(), LoginKeycloakResponseModel.class)
        );
    }
}
