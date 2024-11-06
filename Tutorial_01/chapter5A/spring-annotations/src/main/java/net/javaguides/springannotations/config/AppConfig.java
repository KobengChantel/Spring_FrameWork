package net.javaguides.springannotations.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import net.javaguides.springannotations.service.VegPizza;
import net.javaguides.springannotations.service.Pizza;
//using @bean annotation and configuring a bean name
@Configuration
public class AppConfig {
    @Bean(name = "vegPizzaBean")
    public Pizza vegpizza(){
        return new VegPizza();
    }
}
