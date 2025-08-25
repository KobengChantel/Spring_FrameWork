package tacos.web;

// Spring annotations and interfaces for dependency injection and type conversion
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// Application-specific classes for ingredients and repository
import tacos.Ingredient;
import tacos.data.IngredientRepository;

// 👉 This component converts a String ID into an Ingredient object by looking it up in the IngredientRepository.

@Component  // Marks this class as a Spring bean, so it can be auto-detected and used in type conversion
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  private IngredientRepository ingredientRepo;
  // Repository used to fetch Ingredient objects from the database

  // Constructor injection of the IngredientRepository
  @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  // Converts a String ID to an Ingredient by querying the repository
  @Override
  public Ingredient convert(String id) {
    return ingredientRepo.findById(id).orElse(null);
    // Returns the Ingredient if found, otherwise returns null
  }

}
