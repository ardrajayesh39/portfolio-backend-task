
package com.backend.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.portfolio.model.Project;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    // Custom query methods can be added here, if necessary

    // Example of a custom query method (if required)
    // Optional<Project> findByName(String name);  // Find projects by name, if needed
}

