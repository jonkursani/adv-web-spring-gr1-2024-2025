package dev.jonkursani.restapigr1.services;

import dev.jonkursani.restapigr1.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr1.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr1.dtos.department.UpdateDepartmentRequest;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAll();
    DepartmentDto create(CreateDepartmentRequest request);
    DepartmentDto update(Integer id, UpdateDepartmentRequest request);
}