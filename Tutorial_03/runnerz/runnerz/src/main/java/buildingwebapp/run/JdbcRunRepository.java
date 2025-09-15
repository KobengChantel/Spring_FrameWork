package buildingwebapp.run;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

// This is a database-backed implementation of a repository that manages Run entities using JDBC for persistent storage in a running/fitness tracking application.

@Repository // Spring annotation marking this as a data access component
public class JdbcRunRepository implements RunRepository {

    // Spring's JdbcClient for simplified database operations
    private final JdbcClient jdbcClient;

    /**
     * Constructor injection of JdbcClient dependency
     * @param jdbcClient Spring's JDBC client for database operations
     */
    public JdbcRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    /**
     * Retrieves all runs from the database
     * @return List of all runs from the 'run' table
     */
    public List<Run> findAll() {
        return jdbcClient.sql("select * from run") // SQL query to get all runs
                .query(Run.class) // Map results to Run objects
                .list(); // Return as a list
    }

    /**
     * Finds a run by its ID from the database
     * @param id The ID of the run to find
     * @return Optional containing the run if found, empty if not found
     */
    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT id,title,started_on,completed_on,miles,location FROM Run WHERE id = :id" ) // Parameterized query
                .param("id", id) // Bind the ID parameter
                .query(Run.class) // Map result to Run object
                .optional(); // Return as Optional
    }

    /**
     * Creates a new run in the database
     * @param run The run object to create
     */
    public void create(Run run) {
        // Execute INSERT statement with all run properties
        var updated = jdbcClient.sql("INSERT INTO Run(id,title,started_on,completed_on,miles,location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString())) // Bind parameters
                .update(); // Execute the update

        // Assert that exactly one row was affected, throw exception if not
        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

    /**
     * Updates an existing run in the database
     * @param run The updated run data
     * @param id The ID of the run to update
     */
    public void update(Run run, Integer id) {
        // Execute UPDATE statement for the specified ID
        var updated = jdbcClient.sql("update run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), id)) // Bind parameters
                .update(); // Execute the update

        // Assert that exactly one row was affected, throw exception if not
        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    /**
     * Deletes a run from the database by its ID
     * @param id The ID of the run to delete
     */
    public void delete(Integer id) {
        // Execute DELETE statement with parameterized query
        var updated = jdbcClient.sql("delete from run where id = :id")
                .param("id", id) // Bind the ID parameter
                .update(); // Execute the update

        // Assert that exactly one row was affected, throw exception if not
        Assert.state(updated == 1, "Failed to delete run " + id);
    }

    /**
     * Returns the total count of runs in the database
     * @return Number of runs in the database
     */
    public int count() {
        // Query all runs and get the size of the result set
        return jdbcClient.sql("select * from run").query().listOfRows().size();
    }

    /**
     * Saves multiple runs to the database
     * @param runs List of runs to save
     */
    public void saveAll(List<Run> runs) {
        // Create each run individually using the create method
        runs.stream().forEach(this::create);
    }

    /**
     * Finds all runs that match a specific location in the database
     * @param location The location to filter by
     * @return List of runs at the specified location
     */
    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("select * from run where location = :location") // Parameterized query for location
                .param("location", location) // Bind the location parameter
                .query(Run.class) // Map results to Run objects
                .list(); // Return as a list
    }
}