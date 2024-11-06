package net.javaguides.springannotations.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import net.javaguides.springannotations.service.Pizza;
import net.javaguides.springannotations.service.VegPizza;
import net.javaguides.springannotations.controller.PizzaController;

@Configuration
public class AppConfig {
//injecting dependencies

@Bean
    public Pizza vegPizza(){
      return new VegPizza();
    }
    @Bean
    public Pizza nonVegPizza(){
    return new VegPizza();
    }
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public PizzaController pizzaController(){
    return new PizzaController(vegPizza());
    }
}
