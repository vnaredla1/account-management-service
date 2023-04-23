package com.naredla.accountmanagementservice.store

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "account-user-details")
class AccountUser implements Serializable{

    @Id
    private String id
    private String firstName
    private String lastName
    private Integer age

    String getId(){
        return id
    }

    void setId(String id){
        this.id = id
    }

    String getFirstName(){
        return firstName
    }

    void setFirstName(String firstName){
        this.firstName = firstName
    }

    String getLastName(){
        return lastName
    }

    void setAge(Integer age){
        this.age = age
    }

    String getAge(){
        return age
    }

    void setLastName(String lastName){
        this.lastName = lastName
    }

    AccountUser(){}

    AccountUser(String id, String firstName, String lastname, Integer age){
        super
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.age = age
    }

    String toString(){
        return 'User [ id: ' + id + ', name: ' + firstName +' '+ lastName +', age: '+ age +']'
    }
}
