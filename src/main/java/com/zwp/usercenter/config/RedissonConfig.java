package com.zwp.usercenter.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson配置
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
//Spring Boot 的 @ConfigurationProperties 注解，指定读取配置文件中 spring.redis 前缀的配置
@Data
public class RedissonConfig {

    private String host;
    // Spring Boot 会尝试将 application.yml 中 spring.redis.host 的值注入到这个属性中。

    private String port;

    private String password;

    @Bean
    public RedissonClient redissonClient() {
        // 1.创建配置
        Config config = new Config();
        String redisAddress = String.format("redis://%s:%s", host, port);// redis://127.0.0.1:6379
        config.useSingleServer().setAddress(redisAddress).setDatabase(3).setPassword(password);// 配置 Redisson 使用单机模式连接 Redis 服务器
        // 2.创建实例
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}
