package tacos; // Declares that this class belongs to the 'tacos' package

import lombok.Data; // Lombok annotation that auto-generates getters, setters, equals, hashCode, and toString

@Data // Automatically creates boilerplate methods like getters, setters, and more
public class Ingredient {

  // Immutable fields for ingredient properties
  private final String id;   // Unique identifier for the ingredient
  private final String name; // Human-readable name of the ingredient
  private final Type type;   // Category/type of the ingredient

  // Enum representing possible ingredient types
  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }
}
