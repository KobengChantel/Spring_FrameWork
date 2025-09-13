package tacos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

// 👉 This class represents a Taco entity with an auto-generated ID, a name, a creation date, and a list of ingredients, enforcing validation rules on the name and ingredient list.

@Data
@Entity  // Marks this class as a JPA entity
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) // Auto-generates primary key value
  private Long id;

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long") // Validates taco name
  private String name;

  private Date createdAt = new Date(); // Stores the creation timestamp

  @Size(min=1, message="You must choose at least 1 ingredient") // Validates that at least one ingredient is selected
  @ManyToMany() // Defines a many-to-many relationship with Ingredient
  private List<Ingredient> ingredients = new ArrayList<>();

  // Adds an ingredient to the taco
  public void addIngredient(Ingredient ingredient) {
    this.ingredients.add(ingredient);
  }

}
