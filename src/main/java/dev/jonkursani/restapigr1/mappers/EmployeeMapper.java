package dev.jonkursani.restapigr1.mappers;

import dev.jonkursani.restapigr1.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr1.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    // source: employee entity -> destination: employee dto
    EmployeeDto toDto(Employee employee);
}