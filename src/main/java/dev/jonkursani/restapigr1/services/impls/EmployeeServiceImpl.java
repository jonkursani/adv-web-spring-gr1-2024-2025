package dev.jonkursani.restapigr1.services.impls;

import dev.jonkursani.restapigr1.dtos.employee.CreateEmployeeRequest;
import dev.jonkursani.restapigr1.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr1.dtos.employee.UpdateEmployeeRequest;
import dev.jonkursani.restapigr1.entities.Employee;
import dev.jonkursani.restapigr1.exceptions.employee.EmailAlreadyExistsException;
import dev.jonkursani.restapigr1.exceptions.employee.EmployeeNotFoundException;
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

    @Override
    public EmployeeDto create(CreateEmployeeRequest request) {
        if (repository.existsEmployeeByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        var employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());

//        var departmentDto = departmentService.findById(request.getDepartmentId());
//        var department = departmentMapper.toEntity(departmentDto);

        var department = departmentMapper.toEntity(departmentService.findById(request.getDepartmentId()));
        employee.setDepartment(department);
        employee.setHireDate(request.getHireDate());
        employee.setEmail(request.getEmail());

//        var createdEmployee = repository.save(employee);
//        return employeeMapper.toDto(createdEmployee);

        return employeeMapper.toDto(repository.save(employee));
    }

    @Override
    public EmployeeDto update(Integer id, UpdateEmployeeRequest request) {
        var employeeFromDb = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

//        if (!employeeFromDb.getEmail().equals(request.getEmail())) {
//            if (repository.existsEmployeeByEmail(request.getEmail())) {
//                throw new EmailAlreadyExistsException(request.getEmail());
//            }
//        }

        if (!employeeFromDb.getEmail().equals(request.getEmail()) &&
                repository.existsEmployeeByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        employeeFromDb.setFirstName(request.getFirstName());
        employeeFromDb.setLastName(request.getLastName());
        var department = departmentMapper.toEntity(departmentService.findById(request.getDepartmentId()));
        employeeFromDb.setDepartment(department);
        employeeFromDb.setHireDate(request.getHireDate());
        employeeFromDb.setEmail(request.getEmail());

        return employeeMapper.toDto(repository.save(employeeFromDb));
    }







}