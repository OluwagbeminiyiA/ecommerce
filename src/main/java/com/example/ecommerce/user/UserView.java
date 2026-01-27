package com.example.ecommerce.user;
import java.time.LocalDateTime;

public class UserView {
        private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
