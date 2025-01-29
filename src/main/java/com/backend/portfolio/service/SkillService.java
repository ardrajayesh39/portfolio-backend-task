package com.backend.portfolio.service;

import com.backend.portfolio.model.Skill;
import com.backend.portfolio.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Optional<Skill> getSkillById(UUID id) {
        return skillRepository.findById(id); // Use UUID here
    }

    // Create a new skill
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    // Update an existing skill (including name and icon)
    public Skill updateSkill(UUID id, String name, String icon) {
        Optional<Skill> optionalSkill = skillRepository.findById(id);
        if (optionalSkill.isPresent()) {
            Skill existingSkill = optionalSkill.get();
            existingSkill.setName(name);  // Update skill name
            existingSkill.setIcon(icon);  // Update skill icon
            return skillRepository.save(existingSkill);
        } else {
            return null; // Skill not found
        }
    }

    // Delete a skill
    public boolean deleteSkill(UUID id) {
        if (skillRepository.existsById(id)) {
            skillRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
