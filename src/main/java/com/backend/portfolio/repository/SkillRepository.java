package com.backend.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.portfolio.model.Skill;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {
    // Custom query methods can be added here, if necessary

    // Example of a custom query method (if required)
    // Optional<Skill> findByName(String name);  // Find skills by name, if needed
}
