package tacos; // Declares that this class belongs to the 'tacos' package

import org.springframework.boot.SpringApplication; // Provides a way to launch a Spring Boot application
import org.springframework.boot.autoconfigure.SpringBootApplication; // Enables auto-configuration and component scanning for Spring Boot

@SpringBootApplication // Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
public class TacoCloudApplication { // Our Taco Cloud application where we will run our program

	public static void main(String[] args) {
		// Launches the Spring Boot application
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}
