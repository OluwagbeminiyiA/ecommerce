package com.example.ecommerce;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.product.ProductRepository;
import com.example.ecommerce.user.Role;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DatabasePreLoader {

    private final static Logger logger = LoggerFactory.getLogger(DatabasePreLoader.class);

    @Bean
    CommandLineRunner init(UserRepository userRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder){
        return args -> {
            userRepository.save(new User("name", "gagbedejobi@gmail.com", passwordEncoder.encode("neeyee"), Role.CUSTOMER, LocalDateTime.now()));
            userRepository.save(new User("Niyi", "niyi@example.com", passwordEncoder.encode("password"), Role.CUSTOMER, LocalDateTime.now()));

            logger.info("Finished Loading Users...");

            productRepository.save(new Product("Laptop", "High performance laptop", BigDecimal.valueOf(1200.00), 50L, "Electronics", LocalDateTime.now()));
            productRepository.save(new Product("Smartphone", "Latest model smartphone", BigDecimal.valueOf(800.00), 100L, "Electronics", LocalDateTime.now()));
            logger.info("Preloaded products into the database.");
        };
    }
}
