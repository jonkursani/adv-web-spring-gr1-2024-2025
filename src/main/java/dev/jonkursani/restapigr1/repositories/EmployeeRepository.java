package dev.jonkursani.restapigr1.repositories;

import dev.jonkursani.restapigr1.entities.Department;
import dev.jonkursani.restapigr1.entities.Employee;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findAllByDepartment(Department department);
    boolean existsEmployeeByEmail(String email);
}