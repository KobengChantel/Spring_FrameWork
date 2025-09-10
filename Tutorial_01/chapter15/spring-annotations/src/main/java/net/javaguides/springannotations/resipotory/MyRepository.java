package net.javaguides.springannotations.resipotory;

import org.springframework.stereotype.Repository;

// This class is a simple Spring repository component that provides data access or storage logic.

@Repository
public class MyRepository {

    // Method that returns a simple greeting message
    public String hello() {
        return "Hello Repository!";
    }
}
