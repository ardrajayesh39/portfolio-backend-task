package com.backend.portfolio.service;

import com.backend.portfolio.model.Project;
import com.backend.portfolio.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(UUID id) {
        return projectRepository.findById(id); // Use UUID here
    }

    public Project createProject(Project project, byte[] image) {
        if (image != null) {
            project.setImage(image); // Save the image
        }
        return projectRepository.save(project);
    }

    public Project updateProject(UUID id, String title, String description, byte[] image) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            Project existingProject = optionalProject.get();
            existingProject.setTitle(title);
            existingProject.setDescription(description);
            if (image != null) {
                existingProject.setImage(image); // Update image if provided
            }
            return projectRepository.save(existingProject);
        } else {
            return null; // Project not found
        }
    }

    public boolean deleteProject(UUID id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
