package com.backend.portfolio.model;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    
    private String title;
    private String description;

    @Lob
    @Column(name = "image", columnDefinition = "BYTEA")
    private byte[] image;  // Store image as byte array

    // Default constructor (necessary for JPA)
    public Project() {}

    // Constructor to create a project with title, description, and image
    public Project(String title, String description, byte[] image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    // Optional: Override toString() method for better logging
    @Override
    public String toString() {
        return "Project{id=" + id + ", title='" + title + "', description='" + description + "'}";
    }
}
