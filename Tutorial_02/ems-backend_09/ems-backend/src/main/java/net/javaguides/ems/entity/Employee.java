package net.javaguides.ems.entity;

// JPA annotations for mapping Java objects to database tables
import jakarta.persistence.*;
// Lombok annotation to generate getter methods
import lombok.Getter;
// Lombok annotation to generate setter methods
import lombok.Setter;
// Lombok annotation to generate a no-argument constructor
import lombok.NoArgsConstructor;
// Lombok annotation to generate a constructor with all fields
import lombok.AllArgsConstructor;

// This class represents the Employee database entity and maps employee details to the "employees" table.

@Getter  // Generates getters for all fields
@Setter  // Generates setters for all fields
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor  // Generates a no-argument constructor
@Entity // Marks this class as a JPA entity (mapped to a database table)
@Table(name = "employees") // Maps the entity to the "employees" table in the database
public class Employee {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates ID values using identity strategy
    private Long id;

    @Column(name = "first_name") // Maps this field to "first_name" column in the table
    private String firstName;

    @Column(name = "last_name") // Maps this field to "last_name" column in the table
    private String lastName;

    @Column(name = "email_id", nullable = false, unique = true)
    // Maps this field to "email_id" column, makes it mandatory (not null) and unique
    private String email;
}
