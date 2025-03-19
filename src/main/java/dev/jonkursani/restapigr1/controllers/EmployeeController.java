package dev.jonkursani.restapigr1.controllers;

import dev.jonkursani.restapigr1.dtos.employee.CreateEmployeeRequest;
import dev.jonkursani.restapigr1.dtos.employee.EmployeeDto;
import dev.jonkursani.restapigr1.dtos.employee.UpdateEmployeeRequest;
import dev.jonkursani.restapigr1.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping // api/v1/employees?departmentId=1
    public ResponseEntity<List<EmployeeDto>> findAll(@RequestParam(required = false) Integer departmentId) {
        return ResponseEntity.ok(service.findAll(departmentId));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@Valid @RequestBody CreateEmployeeRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Integer id,
                                              @Valid @RequestBody UpdateEmployeeRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }







}