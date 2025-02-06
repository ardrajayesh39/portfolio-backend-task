package com.backend.portfolio.service;
 
import com.backend.portfolio.model.User;
import com.backend.portfolio.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
 
import java.util.Optional;
 
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
 
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
 
    public String registerUser(String username, String password) {
        // Check if user already exists
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            return "User already exists";
        }
    
        // Encode the password
        String hashedPassword = passwordEncoder.encode(password);
    
        // Create and save the new user
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        userRepository.save(user);
    
        // Log the user registration for debugging (use proper logging framework)
        // Log.info("User registered: " + username); 
    
        return "User registered successfully";
    }
    
    
    public boolean authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }
}