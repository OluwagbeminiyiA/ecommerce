package com.example.ecommerce.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Configuration
class UserPreLoader{

    private static final Logger logger = LoggerFactory.getLogger(UserPreLoader.class);

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            userRepository.save(new User("name", "gagbedejobi@gmail.com", passwordEncoder.encode("neeyee"), Role.CUSTOMER, LocalDateTime.now()));
                        userRepository.save(new User("Niyi", "niyi@example.com", passwordEncoder.encode("password"), Role.CUSTOMER, LocalDateTime.now()));
        };
    }
}
