package com.tony.department_service.usecases;

import com.tony.department_service.application.dto.CreateDepartmentDTO;
import com.tony.department_service.application.dto.UpdateDepartmentDTO;
import com.tony.department_service.application.usecases.CreateDepartmentUseCase;
import com.tony.department_service.application.usecases.GetDepartmentByIdOrThrowUseCase;
import com.tony.department_service.application.usecases.UpdateDepartmentUseCase;
import com.tony.department_service.domain.exceptions.DepartmentException;
import com.tony.department_service.infraestructure.models.Department;
import com.tony.department_service.infraestructure.repositories.DepartmentRepository;
import com.tony.department_service.shared.DatabaseResetManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

@TestExecutionListeners(
        listeners = {DatabaseResetManager.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@SpringBootTest
public class TestUpdateDepartmentUseCase {
    @Autowired
    private DepartmentRepository repo;
    @Autowired
    private CreateDepartmentUseCase createDepartmentUseCase;

    @Autowired
    private UpdateDepartmentUseCase updateDepartmentUseCase;

    @Autowired
    private GetDepartmentByIdOrThrowUseCase getDepartmentByIdOrThrowUseCase;

    public Department getDepartmentForTest(){
        CreateDepartmentDTO dto= CreateDepartmentDTO.builder()
                .name("Machine Learning")
                .leadId("356")
                .description("The department of DevOps")
                .build();

        return createDepartmentUseCase.execute(dto);
    }

    @Test
    void shouldUpdateDepartmentSuccessfully(){
        Department department= getDepartmentForTest();

        updateDepartmentUseCase.execute(UpdateDepartmentDTO
                .builder()
                .name("Data Science")
                .description("Department for Data Science")
                .build(), department.getId());

        Department updatedDepartment = getDepartmentByIdOrThrowUseCase.execute(department.getId());

        Assertions.assertEquals("Data Science", updatedDepartment.getName());
        Assertions.assertEquals("Department for Data Science", updatedDepartment.getDescription());
    }

    @Test
    void shouldFailToUpdateDepartmentWithExistingName(){
        createDepartmentUseCase.execute(CreateDepartmentDTO.builder()
                        .name("IA Department")
                        .description("The IA deparment")
                        .id("343")
                        .leadId("4334")
                .build());
        Department department=getDepartmentForTest();

        Assertions.assertThrows(DepartmentException.class, ()-> updateDepartmentUseCase.execute(UpdateDepartmentDTO.builder()
                        .name("IA Department")
                .build(), department.getId()));


    }
}
