package com.tony.department_service.usecases;

import com.tony.department_service.application.dto.CreateDepartmentDTO;
import com.tony.department_service.application.usecases.CreateDepartmentUseCase;
import com.tony.department_service.application.usecases.DeleteDepartmentUseCase;
import com.tony.department_service.application.usecases.GetDepartmentByIdOrThrowUseCase;
import com.tony.department_service.application.usecases.UpdateDepartmentUseCase;
import com.tony.department_service.domain.exceptions.NotFoundException;
import com.tony.department_service.infraestructure.models.Department;
import com.tony.department_service.infraestructure.models.Employee;
import com.tony.department_service.infraestructure.repositories.DepartmentRepository;
import com.tony.department_service.shared.DatabaseResetManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

import java.util.List;

@TestExecutionListeners(
        listeners = {DatabaseResetManager.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@SpringBootTest
public class TestDeleteDepartmentUseCase {
    @Autowired
    private DepartmentRepository repo;
    @Autowired
    private CreateDepartmentUseCase createDepartmentUseCase;


    @Autowired
    private GetDepartmentByIdOrThrowUseCase getDepartmentByIdOrThrowUseCase;

    @Autowired
    private DeleteDepartmentUseCase deleteDepartmentUseCase;



    public Department getDepartmentForTest(){
        CreateDepartmentDTO dto= CreateDepartmentDTO.builder()
                .name("Machine Learning")
                .leadId("356")
                .description("The department of DevOps")
                .build();

        return createDepartmentUseCase.execute(dto);
    }

    @Test
    void shouldDeleteDepartmentSuccessfully(){
        Department department =getDepartmentForTest();

        deleteDepartmentUseCase.execute(department.getId());

        Assertions.assertThrows(NotFoundException.class,()-> getDepartmentByIdOrThrowUseCase.execute(department.getId()));

    }


}
