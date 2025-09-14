package net.javaguides.ems;

import org.springframework.boot.SpringApplication;
// Annotation to enable Spring Boot’s auto-configuration and component scanning
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This is the main class that starts the Spring Boot Employee Management System application.

@SpringBootApplication  // Marks this class as a Spring Boot application
public class EmsBackendApplication {

    public static void main(String[] args) {
        // Launches the Spring Boot application
        SpringApplication.run(EmsBackendApplication.class, args);
    }
}
