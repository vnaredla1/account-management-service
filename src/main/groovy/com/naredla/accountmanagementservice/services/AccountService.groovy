package com.naredla.accountmanagementservice.services

import com.naredla.accountmanagementservice.repositories.UserAccountRepository
import com.naredla.accountmanagementservice.store.AccountUser
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException

@Slf4j
@Service
class AccountService {

    private RedisService redisService

    @Autowired
    UserAccountRepository userAccountRepository

    AccountService(RedisService redisService){
        this.redisService = redisService
    }

    List<AccountUser> getAllAccounts(){
        return userAccountRepository.findAll()
    }

    void saveAccountUser(AccountUser accountUser){
        userAccountRepository.save(accountUser)
        redisService.setCacheValues(accountUser)
    }

    Optional getAccountUserById(String id){
        Optional accUser = redisService.getCacheValues(id) as Optional
        if (accUser == null){
            log.info('Getting Account User from Storage')
            return userAccountRepository.findById(id)
        }
    }

    void deleteAccountUserById(String id){
        userAccountRepository.deleteById(id)
    }

    void deleteAllAccounts(String password){
        if (password == 'codeRed'){
            userAccountRepository.deleteAll()
        } else {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, 'Password incorrect')
        }
    }
}
