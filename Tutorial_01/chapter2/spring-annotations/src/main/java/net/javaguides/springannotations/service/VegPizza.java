package net.javaguides.springannotations.service;
import org.springframework.stereotype.Component;
@Component
public class VegPizza {
    public String getPizza(){
        return "Veg Pizza!";
    }
}
