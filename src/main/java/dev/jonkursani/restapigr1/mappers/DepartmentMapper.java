package dev.jonkursani.restapigr1.mappers;

import dev.jonkursani.restapigr1.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr1.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr1.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr1.dtos.department.UpdateDepartmentRequest;
import dev.jonkursani.restapigr1.entities.Department;
import dev.jonkursani.restapigr1.entities.Employee;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DepartmentMapper {
    // source: entity -> target: dto
    DepartmentDto toDto(Department department);
    // source: dto -> target: entity
    Department toEntity(CreateDepartmentRequest request);
    Department toEntity(DepartmentDto departmentDto);
    void updateEntityFromDto(UpdateDepartmentRequest request, @MappingTarget Department department);

    @Mapping(target = "employeeCount", source = "employees", qualifiedByName = "countEmployees")
    DepartmentWithEmployeeCount toDepartmentWithEmployeeCount(Department department);

    @Named("countEmployees")
    default int countEmployees(Set<Employee> employees) {
//        if (employees == null) {
//            return 0;
//        }
//
//        return employees.size();

        return employees == null ? 0 : employees.size();
    }
}
