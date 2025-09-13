package net.javaguides.springannotations;

// Primary Spring Boot application class that demonstrates usage of various Spring components
import net.javaguides.springannotations.controller.MyController;
import net.javaguides.springannotations.controller.PizzaController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import net.javaguides.springannotations.service.VegPizza;
import net.javaguides.springannotations.service.MyService;
import net.javaguides.springannotations.resipotory.MyRepository;
import net.javaguides.springannotations.lazy.LazyLoader;
import net.javaguides.springannotations.configurationproperties.AppPropertiesDemo;
import net.javaguides.springannotations.lazy.propertysource.PropertySourceDemo;

@SpringBootApplication
public class SpringAnnotationsApplication {

    public static void main(String[] args) {

        // Bootstraps the Spring application and returns the application context
        ConfigurableApplicationContext context = SpringApplication.run(SpringAnnotationsApplication.class, args);

        // ---------------------------------------
        // Example usage of beans (uncomment as needed)
        // ---------------------------------------

        // Chapter 1-4: PizzaController example
        // PizzaController pizzaController = (PizzaController) context.getBean("pizzaController");
        // System.out.println(pizzaController.getPizza());

        // Chapter 5: VegPizza bean example
        // VegPizza vegPizza = (VegPizza) context.getBean("vegPizzaBean");
        // System.out.println(vegPizza.getPizza());

        // Chapter 6: Basic controller, service, and repository usage
        // MyController myController = context.getBean(MyController.class);
        // System.out.println(myController.hello());
        //
        // MyService myService = context.getBean(MyService.class);
        // System.out.println(myService.hello());
        //
        // MyRepository myRepository = context.getBean(MyRepository.class);
        // System.out.println(myRepository.hello());

        // Chapter 7: LazyLoader example
        // LazyLoader lazyLoader = context.getBean(LazyLoader.class);
        // System.out.println(lazyLoader.LazyLoader());

        // Chapter 8: PropertySourceDemo usage
        PropertySourceDemo propertySourceDemo = context.getBean(PropertySourceDemo.class);
        System.out.println(propertySourceDemo.getHost());
        System.out.println(propertySourceDemo.getEmail());
        System.out.println(propertySourceDemo.getPassword());
        System.out.println(propertySourceDemo.getAppName());
        System.out.println(propertySourceDemo.getHost());
        System.out.println(propertySourceDemo.getAppDesc());

        // AppPropertiesDemo usage
        AppPropertiesDemo appPropertiesDemo = context.getBean(AppPropertiesDemo.class);
        appPropertiesDemo.display();
    }
}
