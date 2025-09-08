package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Ingredient;

// 👉 Repository interface for CRUD operations on Ingredient entities using Spring Data JPA.
public interface IngredientRepository
        extends CrudRepository<Ingredient, String> {

}
