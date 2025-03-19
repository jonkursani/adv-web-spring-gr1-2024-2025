package dev.jonkursani.restapigr1.services;

import dev.jonkursani.restapigr1.dtos.employee.CreateEmployeeRequest;
import dev.jonkursani.restapigr1.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr1.dtos.employee.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll(Integer departmentId);
    EmployeeDto create(CreateEmployeeRequest request);
    EmployeeDto update(Integer id, UpdateEmployeeRequest request);
}