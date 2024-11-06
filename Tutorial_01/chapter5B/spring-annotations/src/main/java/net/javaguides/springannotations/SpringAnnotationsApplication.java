package net.javaguides.springannotations;
//primary annotation
import net.javaguides.springannotations.controller.PizzaController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import net.javaguides.springannotations.service.VegPizza;

@SpringBootApplication
public class SpringAnnotationsApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SpringAnnotationsApplication.class, args);

		PizzaController pizzaController = (PizzaController) (PizzaController) context.getBean("pizzaController");
		System.out.println(pizzaController.getPizza());

//		VegPizza vegPizza = (VegPizza) context.getBean("vegPizzaBean");
//		System.out.println(vegPizza.getPizza());

	}

}
