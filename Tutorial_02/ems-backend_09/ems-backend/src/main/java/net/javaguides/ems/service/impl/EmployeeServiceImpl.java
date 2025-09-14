package net.javaguides.ems.service.impl;

import lombok.AllArgsConstructor;
// Custom exception for resources not found
import net.javaguides.ems.exception.ResourceNotFoundException;
// Repository layer for Employee
import net.javaguides.ems.repository.EmployeeRepository;
// Service interface for Employee operations
import net.javaguides.ems.service.EmployeeService;
// DTO for transferring Employee data
import net.javaguides.ems.dto.EmployeeDto;
// Entity representing Employee in database
import net.javaguides.ems.entity.Employee;
// Marks this class as a Spring service
import org.springframework.stereotype.Service;
// Mapper to convert between Employee entity and DTO
import net.javaguides.ems.mapper.EmployeeMapper;

import java.util.List;
import java.util.stream.Collectors;

// This class implements the EmployeeService interface, providing business logic for CRUD operations on employees.

@Service  // Registers this class as a Spring service bean
@AllArgsConstructor  // Generates constructor with all fields (for dependency injection)
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository; // Repository dependency for DB operations

    // Create a new employee
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Convert DTO to entity
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        // Save entity to database
        Employee savedEmployee = employeeRepository.save(employee);
        // Convert saved entity back to DTO and return
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    // Get employee by ID
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        // Fetch employee or throw exception if not found
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee does not EXIST with given id: " + employeeId));
        // Convert entity to DTO and return
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    // Get all employees
    @Override
    public List<EmployeeDto> getAllEmployees() {
        // Fetch all employees from DB
        List<Employee> employees = employeeRepository.findAll();
        // Convert each employee entity to DTO and collect as list
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    // Update existing employee
    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployeeDto) {
        // Fetch employee or throw exception if not found
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Employee does not exist with the given id: " + employeeId)
        );

        // Update fields from DTO
        employee.setFirstName(updateEmployeeDto.getFirstName());
        employee.setLastName(updateEmployeeDto.getLastName());
        employee.setEmail(updateEmployeeDto.getEmail());

        // Save updated entity to DB
        Employee updatedEmployeeObj = employeeRepository.save(employee);
        // Convert updated entity to DTO and return
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    // Delete employee by ID
    @Override
    public void deleteEmployee(Long employeeId) {
        // Check if employee exists, otherwise throw exception
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Employee does not exist with the given id: " + employeeId)
        );
        // Delete employee by ID
        employeeRepository.deleteById(employeeId);
    }
}
