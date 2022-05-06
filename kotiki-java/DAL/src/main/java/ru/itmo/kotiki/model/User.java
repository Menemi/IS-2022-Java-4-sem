package ru.itmo.kotiki.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private int id;

    @Column(name = "username")
    @NotBlank
    private String username;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    @NotBlank
    private Role role;

    public User() {
    }

    public User(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}