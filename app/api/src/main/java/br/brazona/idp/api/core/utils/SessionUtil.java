package br.brazona.idp.api.core.utils;

import br.brazona.idp.api.core.dtos.SessionDTO;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionUtil {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ConvertUtil convertUtil;

    public void setSession(SessionDTO sessionDTO){
        Gson gson = new Gson();
        String json =  gson.toJson(sessionDTO, SessionDTO.class);

        redisUtil.setValue(sessionDTO.getUserId().toString(),
                convertUtil.stringToBase64(json));
    }

    public SessionDTO getSession (String key){
            Gson gson = new Gson();
            String sessionValue = redisUtil.getValue(key);
            if (sessionValue == null){
                return null;
            }
             return gson.fromJson(
                     convertUtil.bse64ToString(sessionValue),
                     SessionDTO.class);
    }

}
