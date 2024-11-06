package net.javaguides.springannotations.controller;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import net.javaguides.springannotations.service.Pizza;



public class PizzaController {

    private Pizza pizza;


//    @Autowired
    public PizzaController(Pizza pizza) {
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
    public void init(){
        System.out.println("Initialization Logic!");
    }
    public void destroy(){
        System.out.println("Destruction Logic!");
    }
}
