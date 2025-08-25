package tacos;

// Lombok annotation to automatically generate getters, setters, equals, hashCode, and toString
import lombok.Data;

// 👉 This class represents a reference to an Ingredient by its ID, used for linking ingredients to a Taco.

@Data
public class IngredientRef {

  private final String ingredient;
  // Holds the ID of the ingredient this reference points to

}
