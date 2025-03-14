package dev.jonkursani.restapigr1.services.impls;

import dev.jonkursani.restapigr1.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr1.mappers.DepartmentMapper;
import dev.jonkursani.restapigr1.mappers.EmployeeMapper;
import dev.jonkursani.restapigr1.repositories.EmployeeRepository;
import dev.jonkursani.restapigr1.services.DepartmentService;
import dev.jonkursani.restapigr1.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;
    private final EmployeeMapper employeeMapper;


    @Override
    public List<EmployeeDto> findAll(Integer departmentId) {
        if (departmentId != null) {
            // gjeni departamentin prej department service kjo i menaxhon edhe errorat
            // dhe na kthen ni department dto
            var departmentDto = departmentService.findById(departmentId);

            // department dto duhet me kthy ne department entity e perdorim mapperin e department
            // per me dergu si parameter te metoda findAllByDepartment
            var department = departmentMapper.toEntity(departmentDto);
            // metoda findAllByDepartment na kthen employee entity prandaj duhet me kthy ne employee dto
            return  repository.findAllByDepartment(department)
                    .stream()
//                    .map(employee -> employeeMapper.toDto(employee))
                    .map(employeeMapper::toDto)
                    .toList();
        } else {
            return repository.findAll()
                    .stream()
                    .map(employeeMapper::toDto)
                    .toList();
        }
    }
}