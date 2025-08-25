package tacos.data;
// Declares the package name as "tacos.data", typically used for data access or repository classes.

//interface defines a repository for performing basic CRUD-like operations (find all, find by ID, and save) on Ingredient objects

import java.util.Optional;
// Provides the Optional class to handle cases where a value may or may not be present.

import tacos.Ingredient;
// Imports the Ingredient class to be used as an entity in the repository.

// This interface defines the contract for a repository that handles Ingredient data operations.

public interface IngredientRepository {

  Iterable<Ingredient> findAll();
  // Returns all Ingredient objects as an iterable collection.

  Optional<Ingredient> findById(String id);
  // Finds an Ingredient by its ID, wrapped in an Optional to handle missing results.

  Ingredient save(Ingredient ingredient);
  // Saves an Ingredient object (either creating a new one or updating an existing one).

}
