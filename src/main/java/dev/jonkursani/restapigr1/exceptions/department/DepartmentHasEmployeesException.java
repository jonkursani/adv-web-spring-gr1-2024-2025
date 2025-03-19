package dev.jonkursani.restapigr1.exceptions.department;

public class DepartmentHasEmployeesException extends IllegalStateException {
    public DepartmentHasEmployeesException(Integer id) {
        super("Department with id " + id + " has employees");
    }
}