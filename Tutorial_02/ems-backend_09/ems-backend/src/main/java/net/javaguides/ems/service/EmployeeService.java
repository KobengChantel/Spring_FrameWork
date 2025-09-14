package net.javaguides.ems.service;

import net.javaguides.ems.dto.EmployeeDto;
import java.util.List;

// This interface defines the contract for Employee-related business operations (CRUD).

public interface EmployeeService {

    // Create a new employee and return its DTO
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    // Retrieve an employee by ID and return its DTO
    EmployeeDto getEmployeeById(Long employeeId);

    // Retrieve all employees as a list of DTOs
    List<EmployeeDto> getAllEmployees();

    // Update an existing employee with the given ID using new data from DTO
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployeeDto);

    // Delete an employee by ID
    void deleteEmployee(Long employeeId);
}
