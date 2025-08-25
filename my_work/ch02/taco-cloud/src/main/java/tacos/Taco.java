package tacos;
// Declares the package name as "tacos", grouping related classes together.

import java.util.List;
// Imports the List interface from the Java Collections Framework for handling lists of items.

import javax.validation.constraints.NotNull;
// Import for validation annotation to ensure a field cannot be null.

import javax.validation.constraints.Size;
// Import for validation annotation to enforce size constraints on strings or collections.

import lombok.Data;
// Import from Lombok library that automatically generates getters, setters, equals, hashCode, and toString methods.

// This class represents a Taco entity with validation rules for its name and ingredients.

@Data
public class Taco {

  @NotNull
  // Ensures the 'name' field cannot be null.
  @Size(min=5, message="Name must be at least 5 characters long")
  // Requires the 'name' to be at least 5 characters long, otherwise shows a custom message.
  private String name;

  @NotNull
  // Ensures the 'ingredients' list cannot be null.
  @Size(min=1, message="You must choose at least 1 ingredient")
  // Requires at least one ingredient in the list, otherwise shows a custom message.
  private List<Ingredient> ingredients;

}
