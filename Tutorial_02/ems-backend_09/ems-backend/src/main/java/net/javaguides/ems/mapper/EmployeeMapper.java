package net.javaguides.ems.mapper;

import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.dto.EmployeeDto;

// This class provides methods to convert between Employee entity and EmployeeDto for data transfer between layers.

public class EmployeeMapper {

    // Converts an Employee entity to EmployeeDto
    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),         // Set ID from entity
                employee.getFirstName(),  // Set first name from entity
                employee.getLastName(),   // Set last name from entity
                employee.getEmail()       // Set email from entity
        );
    }

    // Converts an EmployeeDto back to Employee entity
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),        // Set ID from DTO
                employeeDto.getFirstName(), // Set first name from DTO
                employeeDto.getLastName(),  // Set last name from DTO
                employeeDto.getEmail()      // Set email from DTO
        );
    }
}
