package dev.jonkursani.restapigr1.repositories;

import dev.jonkursani.restapigr1.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    // JPQL => Java Persistence Query Language
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees") // eager loading
    // @Query("SELECT d, count(e.id) FROM Department d LEFT JOIN Employee e on d.id = e.department.id GROUP BY d.id")
    List<Department> findAllWithEmployeeCount();
}