package net.javaguides.springannotations.service;

import org.springframework.stereotype.Component;
import  org.springframework.context.annotation.Primary;

@Component
//@Primary
public class VegPizza implements Pizza {



    @Override
    public String getPizza() {
        return "Veg Pizza!";
    }
}
