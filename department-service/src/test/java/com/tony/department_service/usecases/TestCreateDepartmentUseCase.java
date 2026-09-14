package com.tony.department_service.usecases;

import com.tony.department_service.application.dto.CreateDepartmentDTO;
import com.tony.department_service.application.usecases.CreateDepartmentUseCase;
import com.tony.department_service.domain.exceptions.DepartmentException;
import com.tony.department_service.infraestructure.models.Department;
import com.tony.department_service.infraestructure.repositories.DepartmentRepository;
import com.tony.department_service.shared.DatabaseResetManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestExecutionListeners;

@TestExecutionListeners(
        listeners = {DatabaseResetManager.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS
)
@SpringBootTest
public class TestCreateDepartmentUseCase {
    @Autowired
    private DepartmentRepository repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CreateDepartmentUseCase useCase;

    @Test
    void shouldCreateDepartmentSuccessfully(){
        CreateDepartmentDTO dto= CreateDepartmentDTO.builder()
                .name("DevOps")
                .leadId("356")
                .description("The department of DevOps")
                .build();

        Department entity=useCase.execute(dto);

        Assertions.assertEquals("DevOps", entity.getName());
        Assertions.assertNotNull(entity.getId());
    }

    @Test
    void shouldFailToCreateDepartmentWithExistingName(){
        CreateDepartmentDTO dto= CreateDepartmentDTO.builder()
                .name("Infrastructure")
                .leadId("356")
                .description("The department of DevOps")
                .build();

        useCase.execute(dto);

        Assertions.assertThrows(DepartmentException.class, ()-> useCase.execute(dto));



    }
}
