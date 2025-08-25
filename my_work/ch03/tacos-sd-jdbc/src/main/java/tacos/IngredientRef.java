package tacos;

// Lombok annotation to generate getters, equals, hashCode, and toString
import lombok.Data;

// 👉 This class represents a reference to an Ingredient by storing only its ID, used to link ingredients to tacos without storing full objects.

@Data
public class IngredientRef {

  private final String ingredient;
  // Holds the ID of the referenced Ingredient

}
