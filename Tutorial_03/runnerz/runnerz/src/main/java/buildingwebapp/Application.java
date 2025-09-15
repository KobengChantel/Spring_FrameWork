package buildingwebapp;

// Commented out basic Spring Boot application setup
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class Application {
//
//  public static void main(String[] args) {
//     SpringApplication.run(Application.class, args);
//  }
//

import buildingwebapp.user.UserHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

// This is the main Spring Boot application class that configures and starts the fitness tracking web application with HTTP client functionality.

@SpringBootApplication // Enables auto-configuration, component scanning, and configuration properties
public class Application {

    /**
     * Main method that starts the Spring Boot application
     * @param args Command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Bean configuration for UserHttpClient using Spring's declarative HTTP interface
     * Creates a proxy implementation of UserHttpClient that automatically handles HTTP requests
     * @return UserHttpClient proxy instance configured for JSONPlaceholder API
     */
    @Bean
    UserHttpClient userHttpClient() {
        // Create RestClient configured with JSONPlaceholder base URL
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");

        // Create HTTP service proxy factory using RestClient adapter
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        // Generate proxy implementation of UserHttpClient interface
        // This automatically implements methods with @GetExchange annotations
        return factory.createClient(UserHttpClient.class);
    }
}