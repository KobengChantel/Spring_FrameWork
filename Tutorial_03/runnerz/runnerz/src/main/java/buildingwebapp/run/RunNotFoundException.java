package buildingwebapp.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// This custom exception is thrown when a run cannot be found and automatically returns HTTP 404 status to the client.

@ResponseStatus(HttpStatus.NOT_FOUND) // Automatically maps this exception to HTTP 404 Not Found response
public class RunNotFoundException extends RuntimeException {

    /**
     * Default constructor that creates the exception with a standard error message
     * When thrown, Spring will automatically return a 404 HTTP status code
     */
    public RunNotFoundException() {
        super("Run Not Found"); // Set the exception message
    }
}