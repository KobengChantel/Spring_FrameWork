package net.javaguides.springannotations.service;

import org.springframework.stereotype.Component;


public class VegPizza implements Pizza {



    @Override
    public String getPizza() {
        return "Veg Pizza!";
    }
}
