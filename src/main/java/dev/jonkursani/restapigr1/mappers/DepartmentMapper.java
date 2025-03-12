package dev.jonkursani.restapigr1.mappers;

import dev.jonkursani.restapigr1.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr1.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr1.dtos.department.UpdateDepartmentRequest;
import dev.jonkursani.restapigr1.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    // source: entity -> target: dto
    DepartmentDto toDto(Department department);
    // source: dto -> target: entity
    Department toEntity(CreateDepartmentRequest request);
    void updateEntityFromDto(UpdateDepartmentRequest request, @MappingTarget Department department);
}
