package tacos;

// Lombok annotations to automatically generate boilerplate code
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 👉 This class represents an ingredient with an ID, name, and type, using Lombok to generate constructors, getters, setters, and other utility methods.

@Data  // Generates getters, setters, equals, hashCode, and toString methods
@AllArgsConstructor  // Generates a constructor with all fields as parameters
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
// Generates a private no-args constructor (required for frameworks like Jackson or JPA)

public class Ingredient {

  private String id;    // Unique identifier for the ingredient
  private String name;  // Display name of the ingredient
  private Type type;    // Type/category of the ingredient (enum)

  // Enum representing the five possible types of ingredients
  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
