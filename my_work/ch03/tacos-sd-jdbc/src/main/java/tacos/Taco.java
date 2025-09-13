package tacos;

// Java utility classes for working with dates and lists
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Validation annotations to enforce constraints on fields
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Spring Data JDBC annotations for table mapping and ID
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

// Lombok annotations to reduce boilerplate
import lombok.Data;
import lombok.EqualsAndHashCode;

// 👉 This class represents a Taco entity with an ID, creation timestamp, name, and a list of ingredient references, using validation constraints and mapped to a database table.

@Data
@Table  // Maps this class to a database table
@EqualsAndHashCode(exclude = "createdAt")
// Excludes createdAt from equals/hashCode to avoid issues comparing Date and Timestamp
public class Taco {

  @Id
  private Long id;
  // Primary key of the taco

  private Date createdAt = new Date();
  // Timestamp for when the taco was created

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;
  // Taco name with validation: at least 5 characters

  @Size(min=1, message="You must choose at least 1 ingredient")
  private List<IngredientRef> ingredients = new ArrayList<>();
  // List of ingredient references with at least one ingredient required

  // Adds an Ingredient to the taco by creating an IngredientRef
  public void addIngredient(Ingredient taco) {
    this.ingredients.add(new IngredientRef(taco.getId()));
  }

}
