package net.javaguides.springannotations.service;

import org.springframework.stereotype.Service;

// This class is a simple Spring service component that contains business logic.

@Service
public class MyService {

    // Method that returns a greeting message
    public String hello() {
        return "hello service";
    }
}
