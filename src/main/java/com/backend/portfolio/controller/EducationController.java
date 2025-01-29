package com.backend.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.portfolio.service.EducationService;
import com.backend.portfolio.model.Education;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/educations")
public class EducationController {

    private final EducationService educationService;

    // Constructor injection
    @Autowired
    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @CrossOrigin(origins = "http://localhost:3000") // Allow requests from the React frontend

    // Method to get all education entries
    @GetMapping
    public List<Education> getAllEducation() {
        return educationService.getAllEducation();
    }

    // Method to get an education entry by ID
    @GetMapping("/{id}")
    public ResponseEntity<Education> getEducationById(@PathVariable UUID id) {
        Optional<Education> education = educationService.getEducationById(id);
        return education.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Method to create a new education entry
    @PostMapping
    public ResponseEntity<Education> createEducation(@RequestBody Education education) {
        Education createdEducation = educationService.createEducation(education);
        return new ResponseEntity<>(createdEducation, HttpStatus.CREATED);
    }

    // Method to update an existing education entry by ID
    @PutMapping("/{id}")
    public ResponseEntity<Education> updateEducation(@PathVariable UUID id, @RequestBody Education education) {
        Optional<Education> existingEducation = educationService.getEducationById(id);
        if (existingEducation.isPresent()) {
            education.setId(id); // Ensure the ID matches for update
            Education updatedEducation = educationService.updateEducation(education);
            return new ResponseEntity<>(updatedEducation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Method to delete an education entry by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducation(@PathVariable UUID id) {
        educationService.deleteEducation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
