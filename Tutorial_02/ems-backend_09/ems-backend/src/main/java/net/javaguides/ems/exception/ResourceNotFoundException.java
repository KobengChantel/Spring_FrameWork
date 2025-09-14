package net.javaguides.ems.exception;

// Provides HTTP status codes for responses
import org.springframework.http.HttpStatus;
// Annotation to define the HTTP response status for an exception
import org.springframework.web.bind.annotation.ResponseStatus;

// This custom exception is thrown when a requested resource (like an Employee) is not found, returning HTTP 404.

@ResponseStatus(value = HttpStatus.NOT_FOUND)
// Marks this exception to automatically return HTTP 404 (Not Found) when thrown
public class ResourceNotFoundException extends RuntimeException {

    // Constructor that passes the error message to the RuntimeException class
    public ResourceNotFoundException(String message){
        super(message);
    }
}
