package dev.jonkursani.restapigr1.exceptions.employee;

import dev.jonkursani.restapigr1.exceptions.EntityNotFoundException;

public class EmployeeNotFoundException extends EntityNotFoundException {
    public EmployeeNotFoundException(Integer id) {
        super("Employee with id " + id + " not found");
    }
}