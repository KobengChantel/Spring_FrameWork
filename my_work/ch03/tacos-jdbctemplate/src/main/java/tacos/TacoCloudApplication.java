package tacos;

// Spring Boot annotations and classes for running a Spring application
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 👉 This is the main entry point for the Taco Cloud Spring Boot application, which bootstraps the application context and starts the embedded server.

@SpringBootApplication  // Marks this as a Spring Boot application and enables component scanning, auto-configuration, and Spring Boot features
public class TacoCloudApplication {

  public static void main(String[] args) {
    SpringApplication.run(TacoCloudApplication.class, args);
    // Starts the Spring application, creating the application context and launching the embedded server
  }

}
