package com.naredla.accountmanagementservice.repositories

import com.naredla.accountmanagementservice.store.AccountUser
import org.springframework.data.mongodb.repository.MongoRepository

interface UserAccRepo extends MongoRepository<AccountUser, String> {
}