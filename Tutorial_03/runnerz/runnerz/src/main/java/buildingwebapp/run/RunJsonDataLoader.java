package buildingwebapp.run;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

// This component loads initial run data from a JSON file during application startup if the database is empty.

@Component // Spring component that will be automatically instantiated and managed
public class RunJsonDataLoader implements CommandLineRunner { // CommandLineRunner runs code after Spring Boot startup

    // Logger for tracking the data loading process
    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    // Jackson ObjectMapper for JSON parsing
    private final ObjectMapper objectMapper;
    // Repository for persisting the loaded run data
    private final RunRepository runRepository;

    /**
     * Constructor with dependency injection
     * @param objectMapper Jackson ObjectMapper for JSON deserialization
     * @param runRepository Repository for run data (specifically qualified to use JDBC implementation)
     */
    public RunJsonDataLoader(ObjectMapper objectMapper, @Qualifier("jdbcRunRepository") RunRepository runRepository) {
        this.objectMapper = objectMapper;
        this.runRepository = runRepository;
    }

    /**
     * Executes after Spring Boot application startup
     * Loads run data from JSON file only if database is empty
     * @param args Command line arguments (not used)
     * @throws Exception If JSON reading or parsing fails
     */
    @Override
    public void run(String... args) throws Exception {
        // Only load data if repository is empty (prevents duplicate data on restarts)
        if(runRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) { // Try-with-resources for automatic stream closure
                // Parse JSON file into Runs wrapper object
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from JSON data and saving to in-memory collection.", allRuns.runs().size());
                // Save all runs to the repository
                runRepository.saveAll(allRuns.runs());
            } catch (IOException e) {
                // Wrap IOException in RuntimeException to propagate startup failure
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            // Log message when data already exists (no loading needed)
            log.info("Not loading Runs from JSON data because the collection contains data.");
        }
    }
}