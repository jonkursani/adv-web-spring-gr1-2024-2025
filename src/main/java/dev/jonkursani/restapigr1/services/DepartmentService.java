package dev.jonkursani.restapigr1.services;

import dev.jonkursani.restapigr1.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr1.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr1.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr1.dtos.department.UpdateDepartmentRequest;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> findAll();
    DepartmentDto findById(Integer id);
    DepartmentDto create(CreateDepartmentRequest request);
    DepartmentDto update(Integer id, UpdateDepartmentRequest request);
    void delete(Integer id);
    List<DepartmentWithEmployeeCount> findAllWithEmployeeCount();
}