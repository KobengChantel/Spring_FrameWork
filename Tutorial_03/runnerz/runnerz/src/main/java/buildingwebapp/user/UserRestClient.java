package buildingwebapp.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

// This component provides HTTP client functionality for consuming user data from the JSONPlaceholder API using Spring's RestClient.

@Component // Spring component for dependency injection
class UserRestClient {

    // Spring's RestClient for making HTTP requests
    private final RestClient restClient;

    /**
     * Constructor that configures the RestClient with base URL
     * @param builder RestClient.Builder provided by Spring for configuration
     */
    public UserRestClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/") // Set base URL for JSONPlaceholder API
                .build(); // Build the configured RestClient instance
    }

    /**
     * Retrieves all users from the external JSONPlaceholder API
     * @return List of all users from the API
     */
    List<User> findAll() {
        return restClient.get() // Initiate GET request
                .uri("/users") // Set the endpoint path
                .retrieve() // Execute the request and retrieve response
                .body(new ParameterizedTypeReference<>() {}); // Deserialize JSON response to List<User>
    }

    /**
     * Retrieves a specific user by ID from the external JSONPlaceholder API
     * @param id The ID of the user to retrieve
     * @return The user with the specified ID
     */
    User findById(Integer id) {
        return restClient.get() // Initiate GET request
                .uri("/users/{id}", id) // Set endpoint path with ID parameter substitution
                .retrieve() // Execute the request and retrieve response
                .body(User.class); // Deserialize JSON response to User object
    }
}