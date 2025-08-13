package tacos.web; // Declares that this class is part of the 'tacos.web' package

import java.util.HashMap; // Provides a map implementation for storing ingredient data
import java.util.Map; // Interface for key-value mappings

import org.springframework.core.convert.converter.Converter; // Allows conversion between types
import org.springframework.stereotype.Component; // Marks this class as a Spring-managed component

import tacos.Ingredient; // Represents an ingredient
import tacos.Ingredient.Type; // Enum for ingredient types

@Component // Registers this class as a bean so Spring can use it automatically
public class IngredientByIdConverter implements Converter<String, Ingredient> {

  // Stores ingredients keyed by their ID
  private Map<String, Ingredient> ingredientMap = new HashMap<>();

  // Populates the ingredient map with predefined ingredients
  public IngredientByIdConverter() {
    ingredientMap.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
    ingredientMap.put("COTO", new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
    ingredientMap.put("GRBF", new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
    ingredientMap.put("CARN", new Ingredient("CARN", "Carnitas", Type.PROTEIN));
    ingredientMap.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
    ingredientMap.put("LETC", new Ingredient("LETC", "Lettuce", Type.VEGGIES));
    ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Type.CHEESE));
    ingredientMap.put("JACK", new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
    ingredientMap.put("SLSA", new Ingredient("SLSA", "Salsa", Type.SAUCE));
    ingredientMap.put("SRCR", new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
  }

  // Converts a given ingredient ID string to its corresponding Ingredient object
  @Override
  public Ingredient convert(String id) {
    return ingredientMap.get(id);
  }
}
