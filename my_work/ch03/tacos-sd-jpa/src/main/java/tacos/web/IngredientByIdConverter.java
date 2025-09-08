package tacos.web;

// Spring annotations for dependency injection and component scanning
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// Application-specific classes
import tacos.Ingredient;
import tacos.data.IngredientRepository;

// 👉 This class converts a String ingredient ID from form submissions into an Ingredient object by looking it up in the IngredientRepository.

@Component  // Marks this as a Spring-managed bean
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private IngredientRepository ingredientRepo;

  @Autowired  // Injects the IngredientRepository dependency
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @Override
  public Ingredient convert(String id) {
    // Fetches the Ingredient by ID; returns null if not found
    return ingredientRepo.findById(id).orElse(null);
  }

}
