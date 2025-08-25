package tacos.data;

// Spring Data interface for basic CRUD operations
import org.springframework.data.repository.CrudRepository;

// Application-specific class for ingredients
import tacos.Ingredient;

// 👉 This interface provides CRUD operations for Ingredient objects using Spring Data JPA, with the ID type as String.

public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {
    // Extends CrudRepository to automatically inherit methods like save, findById, findAll, delete, etc.
}
