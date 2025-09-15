package buildingwebapp.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.Duration;
import java.time.LocalDateTime;

// This record represents a single running exercise entry with validation, duration calculation, and pace calculation functionality.

public record Run(
        Integer id,                    // Unique identifier for the run
        @NotEmpty                      // Validation: title cannot be null or empty
        String title,                  // Descriptive name for the run
        LocalDateTime startedOn,       // When the run began
        LocalDateTime completedOn,     // When the run ended
        @Positive                      // Validation: miles must be a positive number
        Integer miles,                 // Distance covered in miles
        Location location              // Where the run took place (indoor/outdoor)
) {

    /**
     * Compact constructor for validation
     * Automatically called when creating a new Run instance
     */
    public Run {
        // Validate that the completion time is after the start time
        if (!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("Completed On must be after Started On");
        }
    }

    /**
     * Calculates the total duration of the run
     * @return Duration object representing the time between start and completion
     */
    public Duration getDuration() {
        return Duration.between(startedOn, completedOn);
    }

    /**
     * Calculates the average pace in minutes per mile
     * @return Average pace as minutes per mile (rounded down to nearest integer)
     */
    public Integer getAvgPace() {
        // Convert total duration to minutes and divide by miles covered
        return Math.toIntExact(getDuration().toMinutes() / miles);
    }
}