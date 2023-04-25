package com.naredla.accountmanagementservice.services

import com.naredla.accountmanagementservice.store.AccountUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

import java.nio.charset.StandardCharsets

@Service
class RedisService {

    @Autowired
    private RedisTemplate redisTemplate

    @Autowired
    private AccountService accountService


    List getCachedData(){
        List cachedKeys = []
        Object storedKeys = redisTemplate.getConnectionFactory().getConnection().keys('*'.bytes)
        storedKeys.forEach { key -> (
            cachedKeys.add(new String(key, StandardCharsets.UTF_8))
        )}
        return cachedKeys
    }
}
