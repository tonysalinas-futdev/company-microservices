package com.tony.department_service.presentation.controllers;

import com.tony.department_service.application.dto.CreateDepartmentDTO;
import com.tony.department_service.application.dto.UpdateDepartmentDTO;
import com.tony.department_service.application.usecases.*;
import com.tony.department_service.infraestructure.models.Department;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Valid
@RequestMapping(name = "api/v1/department")
@RestController
public class DepartmentControllers {
    private final CreateDepartmentUseCase createDepartmentUseCase;
    private final DeleteDepartmentUseCase deleteDepartmentUseCase;
    private final GetAllDepartments getAllDepartmentsUseCase;
    private final GetDepartmentByIdOrThrowUseCase getDepartmentByIdOrThrowUseCase;
    private final UpdateDepartmentUseCase updateDepartmentUseCase;

    @Operation(description = "Create department", summary = "Create department")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Department> createDepartment(@RequestBody  CreateDepartmentDTO dto){
        return ResponseEntity.status(201).body(createDepartmentUseCase.execute(dto));
    }


    @Operation(description = "Get department by id", summary = "Get department by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Department> getById(@PathVariable String id){
        return ResponseEntity.status(200).body(getDepartmentByIdOrThrowUseCase.execute(id));
    }

    @Operation(description = "Get All departments", summary = "Get All departments")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Department>> getAll(){
        return ResponseEntity.status(201).body(getAllDepartmentsUseCase.execute());
    }

    @Operation(description = "Delete department", summary = "Delete department")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Department> deleteDepartment(@PathVariable String id){
        deleteDepartmentUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(description = "Update department", summary = "Update department")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Department> updateDepartment(@RequestBody UpdateDepartmentDTO dto, @PathVariable String id){

        return ResponseEntity.ok(updateDepartmentUseCase.execute(dto,id));
    }



}
