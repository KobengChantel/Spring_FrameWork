package net.javaguides.springannotations.controller;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import net.javaguides.springannotations.service.VegPizza;

@Component

public class PizzaController {
//using field injection on @Autowired
    @Autowired
    private VegPizza vegPizza;

    //using contructor injection on @Autowired
//@Autowired
//    public PizzaController(VegPizza vegPizza) {
//        this.vegPizza = vegPizza;
//    }

    //Using setterinjection on @Autowired
//    @Autowired
//    public void setVegPizza(VegPizza vegPizza) {
//        this.vegPizza = vegPizza;
//    }

    public String getPizza() {
        return vegPizza.getPizza();
    }
}
