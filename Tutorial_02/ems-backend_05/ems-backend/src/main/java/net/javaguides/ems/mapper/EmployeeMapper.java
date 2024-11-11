package net.javaguides.ems.mapper;
import net.javaguides.ems.entity.Employee;
import net.javaguides.ems.dto.EmployeeDto;


// transporting data between class and sever
//from employee to emploeeDto and visa vesa
public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstname(),
                employeeDto.getLastname(),
                employeeDto.getEmail()
        );
    }
}
