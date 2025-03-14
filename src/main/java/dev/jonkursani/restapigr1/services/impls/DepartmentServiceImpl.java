package dev.jonkursani.restapigr1.services.impls;

import dev.jonkursani.restapigr1.dtos.department.CreateDepartmentRequest;
import dev.jonkursani.restapigr1.dtos.department.DepartmentDto;
import dev.jonkursani.restapigr1.dtos.department.DepartmentWithEmployeeCount;
import dev.jonkursani.restapigr1.dtos.department.UpdateDepartmentRequest;
import dev.jonkursani.restapigr1.exceptions.department.DepartmentNotFoundException;
import dev.jonkursani.restapigr1.mappers.DepartmentMapper;
import dev.jonkursani.restapigr1.repositories.DepartmentRepository;
import dev.jonkursani.restapigr1.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    @Override
    public List<DepartmentDto> findAll() {
        return repository.findAll()
                .stream()
//                .map(department ->
//                        new DepartmentDto(department.getId(), department.getName(), department.getLocation()))
//                .map(department -> mapper.toDto(department))
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public DepartmentDto findById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
//                .map(department -> mapper.toDto(department))
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    @Override
    public DepartmentDto create(CreateDepartmentRequest request) {
        // ktheje dto ne entitet
        var department = mapper.toEntity(request);
        // save ne db
        var createdDepartment = repository.save(department);
        return mapper.toDto(createdDepartment); // ktheje entitetin ne dto
    }

    @Override
    public DepartmentDto update(Integer id, UpdateDepartmentRequest request) {
        // gjeni departamentin a ekziston
        var departmentFromDb = repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        // nese ekziston, beni update entitetin prej te dhenave nga dto
        mapper.updateEntityFromDto(request, departmentFromDb);
        // save departamentin ne db
        var updatedDepartment = repository.save(departmentFromDb);
        // ktheni entitetin e departamentit ne dto
        return mapper.toDto(updatedDepartment);
    }

    @Override
    public void delete(Integer id) {
//        var departmentFromDb = repository.findById(id)
//                .orElseThrow(() -> new DepartmentNotFoundException(id));

        findById(id);
        repository.deleteById(id);
    }

    @Override
    public List<DepartmentWithEmployeeCount> findAllWithEmployeeCount() {
        return repository.findAllWithEmployeeCount()
                .stream()
//                .map(department -> mapper.toDepartmentWithEmployeeCount(department))
                .map(mapper::toDepartmentWithEmployeeCount)
                .toList();
    }





}
