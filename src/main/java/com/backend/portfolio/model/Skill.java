package com.backend.portfolio.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    private String name;
    
    private String icon; // Added icon field

    // Default constructor (necessary for JPA)
    public Skill() {}

    // Constructor to create a skill with name and icon
    public Skill(String name, String icon) {
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

    public String getIcon() {
        return icon; // Getter for icon
    }

    public void setIcon(String icon) {
        this.icon = icon; // Setter for icon
    }

    // Optional: Override toString() method for better logging
    @Override
    public String toString() {
        return "Skill{id=" + id + ", name='" + name + "', icon='" + icon + "'}";
    }
}
