package com.backend.portfolio.model;
 
import jakarta.persistence.*;
import java.util.UUID;
 
@Entity
@Table(name = "userstable")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
 
    @Column(nullable = false, unique = true)
    private String username;
 
    @Column(nullable = false)
    private String password;
 
    // Getters and setters (or use Lombok if preferred)
    public UUID getId() {
        return id;
    }
 
    public void setId(UUID id) {
        this.id = id;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
}
 