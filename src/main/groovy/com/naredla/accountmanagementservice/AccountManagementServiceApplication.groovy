package com.naredla.accountmanagementservice


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching


@SpringBootApplication
@EnableCaching
class AccountManagementServiceApplication {

    static void main(String[] args) {
        SpringApplication.run(AccountManagementServiceApplication, args)
    }
}