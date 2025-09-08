package tacos;

// JPA annotations for entity mapping
import javax.persistence.Entity;
import javax.persistence.Id;

// Lombok annotations for boilerplate code
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 👉 This class represents an Ingredient entity in the database, with fields for id, name, and type (WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE).

@Data
@Entity  // Marks this class as a JPA entity
@AllArgsConstructor  // Generates a constructor with all fields
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)  // Generates a private no-args constructor for JPA
public class Ingredient {

  @Id  // Marks this field as the primary key
  private String id;

  private String name;  // Name of the ingredient
  private Type type;    // Type of ingredient

  // Enum representing types of ingredients
  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

}
