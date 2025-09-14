package tacos;

// Spring Boot annotations and classes for application setup and initialization
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// Application-specific classes for ingredients and repository
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;

// 👉 This is the main Spring Boot application class that starts the Taco Cloud app and pre-loads ingredient data into the database on startup.

@SpringBootApplication  // Marks this class as the main Spring Boot application
public class TacoCloudApplication {

  // Main method to launch the Spring Boot application
  public static void main(String[] args) {
    SpringApplication.run(TacoCloudApplication.class, args);
  }

  // CommandLineRunner bean that loads initial ingredient data into the repository
  @Bean
  public CommandLineRunner dataLoader(IngredientRepository repo) {
    return args -> {
      repo.deleteAll(); // Clears existing data to avoid constraint violations during testing
      repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
      repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
      repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
      repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
      repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
      repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
      repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
      repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
      repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
      repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
      // Pre-populates the database with common taco ingredients
    };
  }

}
