package dev.jonkursani.restapigr1.exceptions.department;

import dev.jonkursani.restapigr1.exceptions.EntityNotFoundException;

public class DepartmentNotFoundException extends EntityNotFoundException {
    public DepartmentNotFoundException(Integer id) {
        super("Department with id " + id + " not found");
    }
}
