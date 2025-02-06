package com.backend.portfolio.controller;

import com.backend.portfolio.model.Skill;
import com.backend.portfolio.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SkillController.class);

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // Get all skills
    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        if (skills.isEmpty()) {
            logger.info("No skills found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    // Get skill by UUID ID
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable UUID id) {
        Optional<Skill> skill = skillService.getSkillById(id);
        return skill.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> {
                    logger.warn("Skill with ID {} not found.", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }

    // Create a new skill with image upload
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Skill> createSkill(@RequestParam("name") String name,
                                             @RequestParam("icon") MultipartFile iconFile) {
        try {
            if (name == null || name.isEmpty() || iconFile.isEmpty()) {
                logger.error("Skill creation failed. Name and icon are required.");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            byte[] iconData = iconFile.getBytes();
            Skill skill = new Skill(name, iconData);
            Skill createdSkill = skillService.createSkill(skill);

            logger.info("Skill created with ID {}", createdSkill.getId());
            return new ResponseEntity<>(createdSkill, HttpStatus.CREATED);
        } catch (IOException e) {
            logger.error("Error processing icon file.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing skill
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Skill> updateSkill(@PathVariable UUID id,
                                             @RequestParam("name") String name,
                                             @RequestParam("icon") MultipartFile iconFile) {
        try {
            if (name == null || name.isEmpty() || iconFile.isEmpty()) {
                logger.error("Skill update failed. Name and icon are required.");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            byte[] iconData = iconFile.getBytes();
            Skill updatedSkill = skillService.updateSkill(id, name, iconData);

            if (updatedSkill != null) {
                return new ResponseEntity<>(updatedSkill, HttpStatus.OK);
            } else {
                logger.warn("Skill with ID {} not found for update.", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            logger.error("Error processing icon file.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a skill
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable UUID id) {
        boolean deleted = skillService.deleteSkill(id);
        if (deleted) {
            logger.info("Skill with ID {} deleted.", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.warn("Skill with ID {} not found for deletion.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
