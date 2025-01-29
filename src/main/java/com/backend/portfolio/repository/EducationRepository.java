package com.backend.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.portfolio.model.Education;
import java.util.UUID;

public interface EducationRepository extends JpaRepository<Education, UUID> {
    // Custom query methods can be added here, if necessary

    // Example of a custom query method (if required)
    // Optional<Education> findByDegree(String degree); // Find education records by degree, if needed
}
