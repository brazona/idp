package br.brazona.idp.api.core.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@EnableCaching
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String REDIS_HOSTNAME;
    @Value("${spring.redis.port}")
    private int REDIS_PORT;

//    @Bean
//    public RedisCacheConfiguration defaultCacheConfiguration() {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofMinutes(120))// 2 horas
//                .disableCachingNullValues()
//                .serializeValuesWith(fromSerializer(new GenericJackson2JsonRedisSerializer()));
//    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, String> redisTemplate(){
           final RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();

           redisTemplate
                   .setKeySerializer(new StringRedisSerializer());
           redisTemplate
                   .setHashKeySerializer(
                           new GenericToStringSerializer<String>(String.class)
                   );
           redisTemplate
                   .setHashValueSerializer(new JdkSerializationRedisSerializer());
           redisTemplate
                   .setValueSerializer(
                           new JdkSerializationRedisSerializer());

        RedisStandaloneConfiguration configuration =
                new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().build();

        JedisConnectionFactory factory =
                new JedisConnectionFactory(
                        configuration,
                        jedisClientConfiguration
                );
        factory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}
