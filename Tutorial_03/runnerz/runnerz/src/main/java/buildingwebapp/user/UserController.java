package buildingwebapp.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// This REST controller provides HTTP endpoints for retrieving user data by delegating to an external HTTP client service.

@RestController // Marks this class as a REST controller that handles HTTP requests and returns JSON responses
@RequestMapping("/users") // Base URL path for all user-related endpoints
class UserController {

    // HTTP client for making external API calls to retrieve user data
    private final UserHttpClient client;

    /**
     * Constructor injection of the user HTTP client dependency
     * @param client HTTP client for fetching user data from external sources
     */
    UserController(UserHttpClient client) {
        this.client = client;
    }

    /**
     * GET /users - Retrieves all users from external API
     * @return List of all users fetched via HTTP client
     */
    @GetMapping("")
    List<User> findAll() {
        return client.findAll();
    }

    /**
     * GET /users/{id} - Retrieves a specific user by ID from external API
     * @param id The ID of the user to retrieve
     * @return The user with the specified ID fetched via HTTP client
     */
    @GetMapping("/{id}")
    User findById(@PathVariable Integer id) {
        return client.findById(id);
    }
}