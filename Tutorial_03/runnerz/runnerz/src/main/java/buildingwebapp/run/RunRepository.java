package buildingwebapp.run;

import java.util.List;
import java.util.Optional;

// This interface defines the contract for run data access operations, allowing different storage implementations (in-memory, database, etc.).

public interface RunRepository {

    /**
     * Retrieves all runs from the data store
     * @return List containing all runs
     */
    List<Run> findAll();

    /**
     * Finds a specific run by its unique identifier
     * @param id The ID of the run to retrieve
     * @return Optional containing the run if found, empty if not found
     */
    Optional<Run> findById(Integer id);

    /**
     * Creates a new run in the data store
     * @param run The run object to create
     */
    void create(Run run);

    /**
     * Updates an existing run with new data
     * @param run The updated run data
     * @param id The ID of the run to update
     */
    void update(Run run, Integer id);

    /**
     * Deletes a run from the data store
     * @param id The ID of the run to delete
     */
    void delete(Integer id);

    /**
     * Returns the total number of runs in the data store
     * @return Count of all runs
     */
    int count();

    /**
     * Saves multiple runs to the data store in a batch operation
     * @param runs List of runs to save
     */
    void saveAll(List<Run> runs);

    /**
     * Finds all runs that match a specific location
     * @param location The location to filter by
     * @return List of runs at the specified location
     */
    List<Run> findByLocation(String location);
}