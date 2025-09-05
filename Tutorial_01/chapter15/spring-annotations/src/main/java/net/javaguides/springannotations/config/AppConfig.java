package net.javaguides.springannotations.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import net.javaguides.springannotations.service.Pizza;
import net.javaguides.springannotations.service.VegPizza;
import net.javaguides.springannotations.controller.PizzaController;
import org.springframework.context.annotation.Lazy;

// This class configures Spring beans for Pizza and PizzaController, demonstrating dependency injection with @Bean and lifecycle management.

@Configuration
public class AppConfig {
    // Injecting dependencies using Spring's @Bean annotation

    // Defines a VegPizza bean that will be lazily initialized (only created when needed)
    @Bean
    @Lazy
    public Pizza vegPizza() {
        return new VegPizza();
    }

    // Defines a nonVegPizza bean but currently returns VegPizza (likely a placeholder or misnamed)
    @Bean
    public Pizza nonVegPizza() {
        return new VegPizza();
    }

    // Defines a PizzaController bean with lifecycle methods (init and destroy)
    // and injects vegPizza() into it
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public PizzaController pizzaController() {
        return new PizzaController(vegPizza());
    }
}
