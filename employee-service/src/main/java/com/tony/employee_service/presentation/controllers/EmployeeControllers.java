package com.tony.employee_service.presentation.controllers;


import com.tony.employee_service.application.usecases.*;
import com.tony.employee_service.infraestructure.dto.CreateEmployeeDTO;
import com.tony.employee_service.infraestructure.dto.UpdateEmployeeDTO;
import com.tony.employee_service.infraestructure.models.EmployeeModel;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Valid
@RequestMapping(name = "api/v1/employees")
@RestController
public class EmployeeControllers {
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;
    private final GetByIdOrThrowUseCase getByIdOrThrowUseCase;
    private final GetAllEmployeesUseCase getAllEmployeesUseCase;
    private final DeleteEmployeeUseCase deleteEmployeeUseCase;

    @Operation(description = "Create employee", summary = "Create employee")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<EmployeeModel> createEmployee(@RequestBody CreateEmployeeDTO dto){
        return ResponseEntity.status(201).body(createEmployeeUseCase.execute(dto));
    }

    @Operation(description = "Get employee by id", summary = "Get employee by id")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id){
        return ResponseEntity.ok(getByIdOrThrowUseCase.execute(id));
    }

    @Operation(description = "Get all employees", summary = "Get all emplooyes")
    @GetMapping()
    public ResponseEntity<List<EmployeeModel>> getAll(){
        return ResponseEntity.ok(getAllEmployeesUseCase.execute());
    }

    @Operation(description = "Update employee", summary = "Update employee")
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable Long id, @RequestBody UpdateEmployeeDTO dto){
        return ResponseEntity.ok(updateEmployeeUseCase.execute(dto, id));
    }


    @Operation(description = "Delete employee", summary = "Delete employee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteEmployee(@PathVariable Long id){
        deleteEmployeeUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
