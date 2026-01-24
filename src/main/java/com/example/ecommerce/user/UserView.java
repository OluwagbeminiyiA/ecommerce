package com.example.ecommerce.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserView {
        private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;
}
