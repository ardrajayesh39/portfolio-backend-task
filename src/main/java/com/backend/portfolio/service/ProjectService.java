package com.backend.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.portfolio.repository.ProjectRepository;
import com.backend.portfolio.model.Project;
import java.util.List;
import java.util.Optional;

@Service  // Marks this class as a service to handle business logic
public class ProjectService {

    // This is where we inject the repository
    private final ProjectRepository projectRepository;

    // Constructor injection: Spring will automatically inject the ProjectRepository into this class
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // Method to get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();  // Calls the repository's built-in findAll() method
    }

    // Method to get a project by its ID
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);  // Calls the repository's built-in findById() method
    }

    // Method to create a new project
    public Project createProject(Project project) {
        return projectRepository.save(project);  // Calls the repository's save() method
    }

    // Method to delete a project by its ID
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);  // Calls the repository's deleteById() method
    }
}
