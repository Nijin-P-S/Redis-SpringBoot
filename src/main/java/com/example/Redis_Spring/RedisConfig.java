package com.example.Redis_Spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    public JedisConnectionFactory getRedisFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                "redis-18706.c212.ap-south-1-1.ec2.cloud.redislabs.com", 18706);
        redisStandaloneConfiguration.setPassword("F1lu42k3nieD99ViIywMn51nynYLt95i");

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);

        jedisConnectionFactory.afterPropertiesSet();

        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> getTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.setConnectionFactory(getRedisFactory());

        return redisTemplate;
    }

}
