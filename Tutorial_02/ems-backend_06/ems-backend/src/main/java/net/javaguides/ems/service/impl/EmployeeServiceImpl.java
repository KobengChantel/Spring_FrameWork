package net.javaguides.ems.service.impl;


import lombok.AllArgsConstructor;
import net.javaguides.ems.repository.EmployeeRepository;
import net.javaguides.ems.service.EmployeeService;
import net.javaguides.ems.dto.EmployeeDto;
import net.javaguides.ems.entity.Employee;
import org.springframework.stereotype.Service;
import net.javaguides.ems.mapper.EmployeeMapper;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(
            EmployeeDto employeeDto
    ) {
        Employee  employee = EmployeeMapper.mapToEmployee(employeeDto);
Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
}
