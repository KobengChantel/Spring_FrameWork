package net.javaguides.ems.controller;

// Lombok annotation to generate a constructor with all class fields
import lombok.AllArgsConstructor;
// Data Transfer Object for Employee
import net.javaguides.ems.dto.EmployeeDto;
// Service layer handling business logic for Employee
import net.javaguides.ems.service.EmployeeService;
// Spring’s HTTP status codes
import org.springframework.http.HttpStatus;
// Wrapper for HTTP responses
import org.springframework.http.ResponseEntity;
// Annotation for REST controllers
import org.springframework.web.bind.annotation.*;
// Import for using @PathVariable annotation
import org.springframework.web.bind.annotation.PathVariable;
// Import for using @PutMapping annotation
import org.springframework.web.bind.annotation.PutMapping;
// Import for using @RequestBody annotation
import org.springframework.web.bind.annotation.RequestBody;

// This class exposes REST APIs for managing Employee resources (CRUD operations).

import java.util.List;

// Marks this class as a REST controller that handles API requests
@RestController
// Generates a constructor with required dependencies using Lombok
@AllArgsConstructor
// Base URL for all employee-related APIs
@RequestMapping("/api/employees")
public class EmployeeController {

    // Dependency injection of the EmployeeService
    private EmployeeService employeeService;

    // Build add employee REST API (Create)
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        // Call service to save new employee
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        // Return the saved employee with HTTP status 201 (Created)
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build get employee by ID REST API (Read - Single Employee)
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeebyId(@PathVariable("id") Long employeeId){
        // Call service to fetch employee by given ID
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        // Return found employee with status 200 (OK)
        return ResponseEntity.ok(employeeDto);
    }

    // Build get all employees REST API (Read - All Employees)
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        // Call service to get list of all employees
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        // Return list of employees with status 200 (OK)
        return ResponseEntity.ok(employees);
    }

    // Build update employee REST API (Update)
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee){
        // Call service to update employee by ID with new data
        EmployeeDto employee = employeeService.updateEmployee(employeeId, updatedEmployee);
        // Return updated employee with status 200 (OK)
        return ResponseEntity.ok(employee);
    }

    // Build delete employee REST API (Delete)
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        // Call service to delete employee by given ID
        employeeService.deleteEmployee(employeeId);
        // Return success message with status 200 (OK)
        return ResponseEntity.ok("Employee deleted successfully!");
    }
}
