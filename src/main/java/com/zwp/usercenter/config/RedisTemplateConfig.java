package com.zwp.usercenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置连接工厂：将传入的 redisConnectionFactory 设置到 redisTemplate 中，以便它可以通过该工厂与 Redis 服务器建立连接。
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // 设置键序列化器：指定键的序列化方式为字符串（RedisSerializer.string()）。这意味着在存储和读取键时会使用字符串格式。
        return redisTemplate;
    }
}
