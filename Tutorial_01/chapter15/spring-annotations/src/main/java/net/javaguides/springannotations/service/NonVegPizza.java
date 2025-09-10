package net.javaguides.springannotations.service;

// This class implements the Pizza interface to represent a non-vegetarian pizza type.

public class NonVegPizza implements Pizza {

    // Implementation of the getPizza() method from the Pizza interface
    @Override
    public String getPizza() {
        return "Non-veg Pizza!";
    }
}
