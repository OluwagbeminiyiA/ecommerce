package com.example.ecommerce.user;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "users")
class User {
    private @Id @GeneratedValue Long id;
    private String name;
    private String email;
    private String password;
    private Role role;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User() {
    }

    public User(String name, String email, String password, Role role, LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name) && Objects.equals(this.email, user.email) && Objects.equals(this.password, user.password) && Objects.equals(this.role, user.role) && Objects.equals(this.createdAt, user.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email, this.password, this.role, this.createdAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", CreatedAt=" + createdAt +
                '}';
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }


    public Role getRole() {
        return this.role;
    }

}
