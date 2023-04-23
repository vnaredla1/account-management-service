package com.naredla.accountmanagementservice.controllers

import com.naredla.accountmanagementservice.services.AccountService
import com.naredla.accountmanagementservice.store.AccountUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping('/account')
class AccountController {
    @Autowired
    private AccountService accountService

    @GetMapping('/getAllUsers')
    List<AccountUser> getAllAccounts(){
        return accountService.getAllAccounts()
    }

    @GetMapping('/getUserById/{id}')
    Optional getUserAccountById(@PathVariable String id){
        return accountService.getAccountUserById(id)
    }

    @PostMapping('/saveUser')
    ResponseEntity saveAccount(@RequestBody AccountUser accountUser){
        boolean validation = StringUtils.hasText(accountUser.getId())
                && StringUtils.hasText(accountUser.getFirstName())
        if (validation){
            accountService.saveAccountUser(accountUser)
            return new ResponseEntity('Request received', HttpStatus.ACCEPTED)
        } else {
            return new ResponseEntity('id and name sariga provide chey ra puka', HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping('/deleteUser')
    ResponseEntity deleteAccount(@RequestBody String id){
            accountService.deleteAccountUserById(id)
            return new ResponseEntity('Account removed', HttpStatus.ACCEPTED)
    }

    @PostMapping
    ResponseEntity deleteDataBase(@RequestBody String password){
        if (StringUtils.hasText(password)){
            accountService.deleteAllAccounts(password)
            return new ResponseEntity('Successfully wiped out the storage', HttpStatus.ACCEPTED)
        }
    }

}
