package com.backend.portfolio.controller;
 
import com.backend.portfolio.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.portfolio.model.User;
 
import java.util.Map;
 
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")  // Enable CORS for React Frontend
public class AuthController {
 
    private final UserService userService;
 
    public AuthController(UserService userService) {
        this.userService = userService;
    }
 
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username and password are required");
        }
 
        String responseMessage = userService.registerUser(user.getUsername(), user.getPassword());
 
        if ("User registered successfully".equals(responseMessage)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }
    }
 
    @PostMapping("/login")
    public ResponseEntity<Map<String, Boolean>> loginUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
 
        boolean isAuthenticated = userService.authenticateUser(username, password);
 
        // Return 200 OK if login is successful, otherwise return 401 Unauthorized
        if (isAuthenticated) {
            return ResponseEntity.ok(Map.of("authenticated", true));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("authenticated", false));
        }
    }
}