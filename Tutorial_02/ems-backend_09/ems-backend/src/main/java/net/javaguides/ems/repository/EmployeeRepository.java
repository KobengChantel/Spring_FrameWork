package net.javaguides.ems.repository;

import net.javaguides.ems.entity.Employee;
// Spring Data JPA repository providing CRUD operations
import org.springframework.data.jpa.repository.JpaRepository;

// This interface acts as the repository layer, providing CRUD operations for Employee entities without writing SQL queries.

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // By extending JpaRepository, it automatically inherits methods like:
    // save(), findById(), findAll(), deleteById(), etc., for Employee entities.
}
