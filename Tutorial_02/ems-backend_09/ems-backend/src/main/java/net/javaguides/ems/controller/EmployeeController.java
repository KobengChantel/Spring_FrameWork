package net.javaguides.ems.controller;

import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    //Build add employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
       EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    //build get employee rest api
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeebyId(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

    //Build get all employee rest api
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
    return ResponseEntity.ok(employees);
    }
    //Build update Employee Rest Api
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employee = employeeService.updateEmployee(
                employeeId, updatedEmployee);
        return ResponseEntity.ok(employee);
    }
    //Build delete employee rest api
@DeleteMapping("{id}")
    public ResponseEntity<String>
 deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully!");
    }    }

