package com.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marks this class as a Spring configuration
public class WebConfig implements WebMvcConfigurer {

    // Override the addCorsMappings method to configure CORS globally
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Apply to all endpoints starting with /api/
            .allowedOrigins("http://localhost:3000")// Allow access from the frontend running on localhost:3000
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these HTTP methods
            .allowedHeaders("*") // Allow all headers in the request
            .allowCredentials(true); // Allow credentials such as cookies
    }
}
