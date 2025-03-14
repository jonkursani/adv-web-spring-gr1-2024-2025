package dev.jonkursani.restapigr1.services;

import dev.jonkursani.restapigr1.dtos.employee.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll(Integer departmentId);
}