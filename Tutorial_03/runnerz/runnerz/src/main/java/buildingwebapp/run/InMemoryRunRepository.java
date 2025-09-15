package buildingwebapp.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

// This is an in-memory implementation of a repository that manages Run entities using an ArrayList for a
// running/fitness tracking application.

@Repository // Spring annotation marking this as a data access component
class InMemoryRunRepository implements RunRepository {

    // Logger for tracking operations and debugging
    private static final Logger log = LoggerFactory.getLogger(InMemoryRunRepository.class);
    // In-memory storage for Run entities using ArrayList
    private final List<Run> runs = new ArrayList<>();

    /**
     * Retrieves all runs from the repository
     * @return List of all runs
     */
    public List<Run> findAll() {
        return runs;
    }

    /**
     * Finds a run by its ID
     * @param id The ID of the run to find
     * @return Optional containing the run if found, throws RunNotFoundException if not found
     */
    public Optional<Run> findById(Integer id) {
        return Optional.ofNullable(runs.stream()
                .filter(run -> run.id() == id) // Filter runs by matching ID
                .findFirst() // Get the first match
                .orElseThrow(RunNotFoundException::new)); // Throw exception if no match found
    }

    /**
     * Creates a new run and adds it to the repository
     * @param run The run object to create
     */
    public void create(Run run) {
        // Create a new Run instance with all the provided details
        Run newRun = new Run(run.id(),
                run.title(),
                run.startedOn(),
                run.completedOn(),
                run.miles(),
                run.location());

        // Add the new run to the in-memory list
        runs.add(newRun);
    }

    /**
     * Updates an existing run with new data
     * @param newRun The updated run data
     * @param id The ID of the run to update
     */
    public void update(Run newRun, Integer id) {
        Optional<Run> existingRun = findById(id); // Find the existing run
        if(existingRun.isPresent()) {
            var r = existingRun.get();
            log.info("Updating Existing Run: " + existingRun.get()); // Log the update operation
            runs.set(runs.indexOf(r), newRun); // Replace the old run with the new one at the same index
        }
    }

    /**
     * Deletes a run by its ID
     * @param id The ID of the run to delete
     */
    public void delete(Integer id) {
        log.info("Deleting Run: " + id); // Log the deletion operation
        runs.removeIf(run -> run.id().equals(id)); // Remove run with matching ID
    }

    /**
     * Returns the total count of runs in the repository
     * @return Number of runs
     */
    public int count() {
        return runs.size();
    }

    /**
     * Saves multiple runs at once
     * @param runs List of runs to save
     */
    public void saveAll(List<Run> runs) {
        runs.stream().forEach(run -> create(run)); // Create each run individually
    }

    /**
     * Finds all runs that match a specific location
     * @param location The location to filter by
     * @return List of runs at the specified location
     */
    public List<Run> findByLocation(String location) {
        return runs.stream()
                .filter(run -> Objects.equals(run.location(), location)) // Filter by location using null-safe comparison
                .toList(); // Convert stream to list
    }

    /**
     * Initialization method that runs after the bean is constructed
     * Populates the repository with sample data
     */
    @PostConstruct
    private void init() {
        // Add first sample run - 30 minute indoor run
        runs.add(new Run(1,
                "Monday Morning Run",
                LocalDateTime.now(), // Start time is current time
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES), // End time is 30 minutes later
                3, // 3 miles
                Location.INDOOR));

        // Add second sample run - 60 minute indoor run
        runs.add(new Run(2,
                "Wednesday Evening Run",
                LocalDateTime.now(), // Start time is current time
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES), // End time is 60 minutes later
                6, // 6 miles
                Location.INDOOR));
    }
}