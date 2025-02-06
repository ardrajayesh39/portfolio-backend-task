package com.backend.portfolio;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
 
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
// @SpringBootApplication(scanBasePackages = "chipbk10.com.example.authservices")
public class PortfolioApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(PortfolioApplication.class, args);
    }
 
}