package com.backend.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.portfolio.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Custom query methods can be added here, if necessary
}
