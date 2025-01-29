package com.backend.portfolio.controller;

import com.backend.portfolio.model.Project;
import com.backend.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Get all projects
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        if (projects.isEmpty()) {
            logger.info("No projects found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get project by UUID ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable UUID id) {
        Optional<Project> project = projectService.getProjectById(id);
        if (project.isPresent()) {
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        } else {
            logger.warn("Project with ID {} not found.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new project
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestParam("title") String title,
                                                 @RequestParam("description") String description,
                                                 @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            byte[] imageBytes = imageFile != null ? imageFile.getBytes() : null;
            Project project = new Project(title, description, imageBytes);
            Project createdProject = projectService.createProject(project, imageBytes);

            logger.info("Project created with ID {}", createdProject.getId());
            return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
        } catch (IOException e) {
            logger.error("Error creating project: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable UUID id,
                                                 @RequestParam("title") String title,
                                                 @RequestParam("description") String description,
                                                 @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            byte[] imageBytes = imageFile != null ? imageFile.getBytes() : null;
            Project updatedProject = projectService.updateProject(id, title, description, imageBytes);

            if (updatedProject != null) {
                return new ResponseEntity<>(updatedProject, HttpStatus.OK);
            } else {
                logger.warn("Project with ID {} not found for update.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            logger.error("Error updating project: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id) {
        try {
            boolean deleted = projectService.deleteProject(id);
            if (deleted) {
                logger.info("Project with ID {} deleted.", id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                logger.warn("Project with ID {} not found for deletion.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error deleting project with ID {}: {}", id, e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
