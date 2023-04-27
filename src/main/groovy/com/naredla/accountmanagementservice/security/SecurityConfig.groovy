package com.naredla.accountmanagementservice.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .antMatchers("/account/**").authenticated()
                .anyRequest().permitAll()
                .and().httpBasic()
                .and().csrf().disable()
                .build()
    }

    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager()
        //TODO: Add a data source to retrieve user data for dynamic validation.
        manager.createUser(User.withUsername("snaredla")
                .password(passwordEncoder().encode("naredlav1"))
                .roles("USER")
                .build())
        return manager
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder()
    }

}
