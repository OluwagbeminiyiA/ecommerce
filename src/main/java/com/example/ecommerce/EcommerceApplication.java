package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication()
public class EcommerceApplication {



    @RequestMapping("/")
    public String home() {
        return "Welcome to the E-commerce Application!";
    }

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
