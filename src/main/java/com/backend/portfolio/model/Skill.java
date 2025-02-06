package com.backend.portfolio.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "skill_new")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    private String name;
    
    @Lob
    @Column(columnDefinition = "BYTEA") // PostgreSQL specific, change if needed for other DBs
    private byte[] icon; // Store icon as byte array

    // Default constructor
    public Skill() {}

    public Skill(String name, byte[] icon) {
        this.name = name;
        this.icon = icon;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Skill{id=" + id + ", name='" + name + "', icon= [binary data]}";
    }
}
