package net.javaguides.springannotations.service;

import org.springframework.stereotype.Component;
import  org.springframework.context.annotation.Primary;

@Component // Marks this class as a Spring-managed bean
@Primary
public class NonVegPizza implements Pizza {

   @Override
   public String getPizza() { // Correct method name to match the interface
      return "Non-veg Pizza!";
   }
}


