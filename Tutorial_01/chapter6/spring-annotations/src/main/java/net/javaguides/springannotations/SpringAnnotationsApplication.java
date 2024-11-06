package net.javaguides.springannotations;
//primary annotation
import net.javaguides.springannotations.controller.MyController;
import net.javaguides.springannotations.controller.PizzaController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import net.javaguides.springannotations.service.VegPizza;
import net.javaguides.springannotations.service.MyService;
import net.javaguides.springannotations.resipotory.MyRepository;
@SpringBootApplication
public class SpringAnnotationsApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SpringAnnotationsApplication.class, args);
//chp1-4
//		PizzaController pizzaController = (PizzaController) (PizzaController) context.getBean("pizzaController");
//		System.out.println(pizzaController.getPizza());
//chp5
//		VegPizza vegPizza = (VegPizza) context.getBean("vegPizzaBean");
//		System.out.println(vegPizza.getPizza());
//chp6
//		MyController myController = context.getBean(MyController.class);
//		System.out.println(myController.hello());

//		MyService myService = context.getBean(MyService.class);
//		System.out.println(myService.hello());

		MyRepository myRepository = context.getBean(MyRepository.class);
		System.out.println(myRepository.hello());
	}

}
