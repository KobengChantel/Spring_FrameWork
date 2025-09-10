package net.javaguides.springannotations.controller;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import net.javaguides.springannotations.service.Pizza;

// This class is a controller that manages a Pizza service bean using dependency injection
// and includes lifecycle methods for initialization and destruction.

public class PizzaController {

    // Dependency on Pizza interface (injected via constructor or setter)
    private Pizza pizza;

    // Constructor-based dependency injection
    // @Autowired can be used here, but it's optional since Spring 4.3+ if there's only one constructor
    public PizzaController(Pizza pizza) {
        this.pizza = pizza;
    }

    // Setter injection with @Autowired (alternative to constructor injection)
    // Uncomment this if you prefer setter-based injection
    /*
    @Autowired
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    */

    // Method to get pizza description from the injected Pizza bean
    public String getPizza() {
        return pizza.getPizza();
    }

    // Initialization logic (called after bean creation if configured in @Bean)
    public void init() {
        System.out.println("Initialization Logic!");
    }

    // Destruction logic (called before bean destruction if configured in @Bean)
    public void destroy() {
        System.out.println("Destruction Logic!");
    }
}
