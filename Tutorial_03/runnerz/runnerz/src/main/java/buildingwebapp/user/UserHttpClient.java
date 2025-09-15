package buildingwebapp.user;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

// This interface defines a declarative HTTP client for consuming external user API endpoints using Spring's HTTP Exchange annotations.

public interface UserHttpClient {

    /**
     * Retrieves all users from the external API
     * Makes a GET request to "/users" endpoint
     * @return List of all users from the external service
     */
    @GetExchange("/users")
    List<User> findAll();

    /**
     * Retrieves a specific user by ID from the external API
     * Makes a GET request to "/{id}" endpoint with path variable substitution
     * @param id The ID of the user to retrieve
     * @return The user with the specified ID from the external service
     */
    @GetExchange("/{id}")
    User findById(Integer id);
}