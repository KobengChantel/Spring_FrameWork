package net.javaguides.springannotations.controller;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import net.javaguides.springannotations.service.Pizza;

@Component

public class PizzaController {

    private Pizza pizza;

    // Constructor injection with @Autowired
    // @Autowired automatically injects a bean of type Pizza.
    // @Qualifier("vegPizza") specifies that we want the "vegPizza" bean specifically injected here.
//    @Autowired
    @Autowired
    public PizzaController(@Qualifier("nonVegPizza")Pizza pizza) {
        this.pizza = pizza;
    }


    // Setter injection with @Autowired
    // This is an optional alternative to constructor injection.
    // Uncomment this method if you want to use setter injection instead.
    // @Autowired
    // public void setPizza(Pizza pizza) {
    //     this.pizza = pizza;
    // }

    // Method to retrieve the pizza description.
    public String getPizza() {
        return pizza.getPizza();
    }
}
