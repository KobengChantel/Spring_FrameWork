package net.javaguides.springannotations.service;

// This class implements the Pizza interface to represent a vegetarian pizza type.

public class VegPizza implements Pizza {

    // Implementation of the getPizza() method from the Pizza interface
    @Override
    public String getPizza() {
        return "Veg Pizza!";
    }
}
