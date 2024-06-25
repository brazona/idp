package br.brazona.idp.api.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisUtil {

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setValue(final String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }
    public String getValue(final String key){
        return redisTemplate.opsForValue().get(key);
    }
    public void deleteValue(final String key){
        redisTemplate.delete(key);
    }
}
