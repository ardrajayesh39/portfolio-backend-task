package com.backend.portfolio.service;
import org.springframework.stereotype.Service;
import com.backend.portfolio.repository.EducationRepository;
import com.backend.portfolio.model.Education;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service  // Marks this class as a service to handle business logic
public class EducationService {

    // Inject the repository
    private final EducationRepository educationRepository;

    // Constructor injection: Spring will automatically inject the EducationRepository
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    // Method to get all education entries
    public List<Education> getAllEducation() {
        return educationRepository.findAll();  // Calls the repository's built-in findAll() method
    }

    // Method to get an education entry by its ID
    public Optional<Education> getEducationById(UUID id) {
        return educationRepository.findById(id);  // Calls the repository's built-in findById() method
    }

    // Method to create a new education entry
    public Education createEducation(Education education) {
        return educationRepository.save(education);  // Calls the repository's save() method
    }

    // Method to delete an education entry by its ID
    public void deleteEducation(UUID id) {
        educationRepository.deleteById(id);  // Calls the repository's deleteById() method
    }

    // Method to update an existing education entry
    public Education updateEducation(Education education) {
        // Check if the education entry exists
        Optional<Education> existingEducation = educationRepository.findById(education.getId());
        if (existingEducation.isPresent()) {
            // Update the education fields here
            Education updatedEducation = existingEducation.get();
            updatedEducation.setDegree(education.getDegree());
            updatedEducation.setInstitution(education.getInstitution());
            updatedEducation.setYear(education.getYear());
            updatedEducation.setPercentage(education.getPercentage());
            
            // Save and return the updated education entry
            return educationRepository.save(updatedEducation);
        } else {
            // Handle the case where the education entry is not found, e.g., throw an exception
            throw new RuntimeException("Education entry not found with id: " + education.getId());
        }
    }
}
