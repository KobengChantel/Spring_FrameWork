package tacos;

// Java utility classes for handling dates and lists
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Validation annotations for input constraints
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Lombok annotation to generate getters, setters, equals, hashCode, and toString
import lombok.Data;

// 👉 This class represents a Taco with a name, creation date, a list of ingredient references, and methods to add ingredients, including validation rules.

@Data
public class Taco {

  private Long id;
  // Unique identifier for the taco

  private Date createdAt = new Date();
  // Timestamp when the taco is created; defaults to current date

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;
  // Name of the taco with validation: must be at least 5 characters

  @Size(min=1, message="You must choose at least 1 ingredient")
  private List<IngredientRef> ingredients = new ArrayList<>();
  // List of ingredient references, validated to have at least one item

  // Adds an ingredient to the taco by creating an IngredientRef from the ingredient's ID
  public void addIngredient(Ingredient taco) {
    this.ingredients.add(new IngredientRef(taco.getId()));
  }

}
