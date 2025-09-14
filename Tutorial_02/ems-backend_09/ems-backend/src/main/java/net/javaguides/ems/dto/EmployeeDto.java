package net.javaguides.ems.dto;

// Lombok annotation to generate a constructor with all class fields
import lombok.AllArgsConstructor;
// Lombok annotation to generate getter methods for all fields
import lombok.Getter;
// Lombok annotation to generate a no-argument constructor
import lombok.NoArgsConstructor;
// Lombok annotation to generate setter methods for all fields
import lombok.Setter;

// This class is a Data Transfer Object (DTO) that carries employee data between layers in the application.

@Getter  // Generates getters for all fields
@Setter  // Generates setters for all fields
@AllArgsConstructor // Generates a constructor with all fields as parameters
@NoArgsConstructor  // Generates a default constructor with no parameters
public class EmployeeDto {

    // Unique identifier for the employee
    private Long id;

    // Employee's first name
    private String firstName;

    // Employee's last name
    private String lastName;

    // Employee's email address
    private String email;
}
