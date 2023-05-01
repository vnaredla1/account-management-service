package com.naredla.accountmanagementservice.services

import com.naredla.accountmanagementservice.store.AccountUser
import groovy.util.logging.Slf4j
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Slf4j
@Service
class RedisService {

    private final String REDIS_KEY = 'redisKey'

    private RedisTemplate redisTemplate

    RedisService(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate
    }

    void setCacheValues(AccountUser accountUser){
        String id = accountUser.id
        redisTemplate.boundHashOps(REDIS_KEY).put(id, accountUser)
        log.info(id + ' is stored in the redis cache')
    }

    AccountUser getCacheValues(String id){
        return redisTemplate.opsForHash().get(REDIS_KEY, id) as AccountUser
    }

    List getCachedData(){
        return redisTemplate.opsForHash().keys('redisKey') as List
    }
}
