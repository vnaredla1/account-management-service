package com.naredla.accountmanagementservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.RedisConnectionFailureException
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfig {

    @Value('${spring.redis.host}')
    private String redisHost

    @Value('${spring.redis.port}')
    private int redisPort

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {

        JedisConnectionFactory jedisConnectionFactory = null

        try {
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration()
            redisStandaloneConfiguration.setDatabase(0)
            redisStandaloneConfiguration.setHostName("localhost")
            redisStandaloneConfiguration.setPort(6379)

            jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration)

            jedisConnectionFactory.getPoolConfig().setMaxTotal(50)
            jedisConnectionFactory.getPoolConfig().setMaxIdle(50)
        } catch (RedisConnectionFailureException e) {
            e.getMessage()
        }

        return jedisConnectionFactory
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate()
        redisTemplate.setConnectionFactory(jedisConnectionFactory())
        return redisTemplate
    }

}
