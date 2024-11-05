package net.javaguides.springannotations.service;

import org.springframework.stereotype.Component;

@Component // Marks this class as a Spring-managed bean
public class NonVegPizza implements Pizza {

   @Override
   public String getPizza() { // Correct method name to match the interface
      return "Non-veg Pizza!";
   }
}


