package net.javaguides.springannotations.service;

public class NonVegPizza implements Pizza {

   @Override
   public String getPizza() { // Correct method name to match the interface
      return "Non-veg Pizza!";
   }
}


