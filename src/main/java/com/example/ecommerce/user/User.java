package com.example.ecommerce.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "users")
class User {
    private @Id @GeneratedValue Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime CreatedAt;

    public User() {
    }

    public User(String name, String email, String password, Role role, LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        CreatedAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(this.id, user.id) && Objects.equals(this.name, user.name) && Objects.equals(this.email, user.email) && Objects.equals(this.password, user.password) && Objects.equals(this.role, user.role) && Objects.equals(this.CreatedAt, user.CreatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.email, this.password, this.role, this.CreatedAt);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", CreatedAt=" + CreatedAt +
                '}';
    }
}
