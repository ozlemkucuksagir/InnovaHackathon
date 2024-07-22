package com.example.hackathon.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="role")
    private String role;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "myUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Transaction> transactions;


    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}
