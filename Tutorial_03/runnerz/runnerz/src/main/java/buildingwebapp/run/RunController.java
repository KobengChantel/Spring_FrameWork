package buildingwebapp.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

// This REST controller provides HTTP endpoints for managing running exercise data, implementing a complete CRUD API for the fitness tracking application.

@RestController // Marks this class as a REST controller that handles HTTP requests and returns JSON responses
@RequestMapping("/api/runs") // Base URL path for all endpoints in this controller
class RunController {

    // Dependency injection of the repository for database operations
    private final JdbcRunRepository runRepository;

    /**
     * Constructor injection of the run repository dependency
     * @param runRepository Repository for run data persistence
     */
    RunController(JdbcRunRepository runRepository) {
        this.runRepository = runRepository;
    }

    /**
     * GET /api/runs - Retrieves all runs
     * @return List of all runs in the system
     */
    @GetMapping
    List<Run> findAll() {
        return runRepository.findAll();
    }

    /**
     * GET /api/runs/{id} - Retrieves a specific run by ID
     * @param id The ID of the run to retrieve
     * @return The run with the specified ID
     * @throws ResponseStatusException with 404 status if run not found
     */
    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);
        // Check if run exists, throw 404 error if not found
        if(run.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found.");
        }
        return run.get();
    }

    /**
     * POST /api/runs - Creates a new run
     * @param run The run data to create (validated using @Valid)
     * Returns HTTP 201 (CREATED) status on success
     */
    @ResponseStatus(HttpStatus.CREATED) // Return 201 status code for successful creation
    @PostMapping
    void create(@Valid @RequestBody Run run) { // @Valid triggers validation annotations on Run record
        runRepository.create(run);
    }

    /**
     * PUT /api/runs/{id} - Updates an existing run
     * @param run The updated run data (validated using @Valid)
     * @param id The ID of the run to update
     * Returns HTTP 204 (NO_CONTENT) status on success
     */
    @ResponseStatus(HttpStatus.NO_CONTENT) // Return 204 status code for successful update
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) { // @Valid triggers validation
        runRepository.update(run, id);
    }

    /**
     * DELETE /api/runs/{id} - Deletes a run by ID
     * @param id The ID of the run to delete
     * Returns HTTP 204 (NO_CONTENT) status on success
     */
    @ResponseStatus(HttpStatus.NO_CONTENT) // Return 204 status code for successful deletion
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        runRepository.delete(id);
    }

    /**
     * GET /api/runs?location={location} - Finds runs by location
     * @param location The location to filter by (passed as query parameter)
     * @return List of runs at the specified location
     * Note: Missing @GetMapping annotation - this endpoint won't work without it
     */
    List<Run> findByLocation(@RequestParam String location) {
        return runRepository.findByLocation(location);
    }
}