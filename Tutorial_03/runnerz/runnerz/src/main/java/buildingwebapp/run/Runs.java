package buildingwebapp.run;

import java.util.List;

// This wrapper record is used for JSON deserialization when loading run data from external files, matching the JSON structure that contains an array of runs.

public record Runs(List<Run> runs) {
    // Simple wrapper record that holds a list of Run objects
    // Used by RunJsonDataLoader to parse JSON files with structure like: {"runs": [...]}
}