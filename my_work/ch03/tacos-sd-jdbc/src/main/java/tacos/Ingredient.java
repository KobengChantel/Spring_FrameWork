package tacos;

// Spring Data annotations and interfaces for relational mapping and persistence
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

// Lombok annotations for boilerplate code generation
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 👉 This class represents an Ingredient entity for Spring Data JDBC, with ID, name, type, and implements Persistable to control persistence behavior.

@Data  // Generates getters, setters, equals, hashCode, and toString
@Table // Maps this class to a database table
@AllArgsConstructor  // Generates a constructor with all fields
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
// Generates a private no-args constructor required by frameworks

public class Ingredient implements Persistable<String> {

    @Id
    private String id;
    // Primary key for the ingredient

    private String name;
    // Ingredient name

    private Type type;
    // Ingredient type (enum)

    @Override
    public boolean isNew() {
        return true;
        // Always treats the entity as new so Spring Data JDBC performs inserts rather than updates
    }

    // Enum representing possible ingredient types
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
