package com.chantel.employeemanagement.employee_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springdoc.core.annotations.OpenAPIDefinition;
//import org.springdoc.core.annotations.Info;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Employee Management API",
//		version = "1.0", description = "API for managing employees"))

public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

}
