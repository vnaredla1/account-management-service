package com.naredla.accountmanagementservice.services

import com.naredla.accountmanagementservice.repositories.UserAccountRepository
import com.naredla.accountmanagementservice.store.AccountUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException

@Service
class AccountService {

    @Autowired
    UserAccountRepository userAccountRepository

    List<AccountUser> getAllAccounts(){
        return userAccountRepository.findAll()
    }

    void saveAccountUser(AccountUser accountUser){
        userAccountRepository.save(accountUser)
    }

    @Cacheable(value = 'User', key = '#id')
    Optional getAccountUserById(String id){
        userAccountRepository.findById(id)
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
