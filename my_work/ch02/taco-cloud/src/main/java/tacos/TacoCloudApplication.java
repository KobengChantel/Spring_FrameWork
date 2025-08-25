package tacos;
// Declares the package name as "tacos", grouping related classes together.

import org.springframework.boot.SpringApplication;
// Provides a convenient way to bootstrap a Spring application.

import org.springframework.boot.autoconfigure.SpringBootApplication;
// Enables Spring Boot's auto-configuration, component scanning, and configuration support in one annotation.

// This class serves as the main entry point for the Taco Cloud Spring Boot application.

@SpringBootApplication   // <1>
// Marks this class as a Spring Boot application, enabling auto-configuration and scanning.
public class TacoCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(TacoCloudApplication.class, args); // <2>
    // Boots up the Spring application, starting the embedded server and initializing the app context.
  }

}
