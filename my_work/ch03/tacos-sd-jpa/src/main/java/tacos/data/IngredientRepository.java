package tacos.data;

// Spring Data repository interface for CRUD operations
import org.springframework.data.repository.CrudRepository;

// Application-specific entity
import tacos.Ingredient;

// 👉 This interface provides CRUD operations for Ingredient entities using Spring Data JDBC, with String as the ID type.

public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {
    // By extending CrudRepository, this interface inherits methods like save(), findById(), findAll(), delete(), etc.
}
