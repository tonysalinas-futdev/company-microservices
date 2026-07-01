package com.tony.employee_service.usecase;

import static org.junit.jupiter.api.Assertions.*;

import com.tony.employee_service.application.dto.CreateEmployeeDTO;
import com.tony.employee_service.application.dto.UpdateEmployeeDTO;
import com.tony.employee_service.application.usecases.CreateEmployeeUseCase;
import com.tony.employee_service.application.usecases.GetByIdOrThrowUseCase;
import com.tony.employee_service.application.usecases.UpdateEmployeeUseCase;
import com.tony.employee_service.domain.entitys.Position;
import com.tony.employee_service.domain.entitys.Role;
import com.tony.employee_service.infraestructure.models.EmployeeModel;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestUpdateEmployeeUseCase {
  @Autowired public CreateEmployeeUseCase createEmployeeUseCase;

  @Autowired public GetByIdOrThrowUseCase getByIdOrThrowUseCase;

  @Autowired public UpdateEmployeeUseCase updateEmployeeUseCase;

  public EmployeeModel createAndSaveEmployeeForTest() {
    CreateEmployeeDTO dto =
        CreateEmployeeDTO.builder()
            .fullName("Juan Carlos Chao")
            .email("carlos@gmail.com")
            .departmentId("4")
            .salary(BigDecimal.valueOf(300L))
            .role(Role.JUNIOR)
            .position(Position.SOFTWARE_ENGINEER)
            .build();

    return createEmployeeUseCase.execute(dto);
  }

  @Test
  void shouldUpdateEmployeeSuccessfully() {
    EmployeeModel model = createAndSaveEmployeeForTest();
    UpdateEmployeeDTO dto =
        UpdateEmployeeDTO.builder()
            .departmentId("7")
            .fullName("New FullName")
            .email("newemail@gmail.com")
            .role(Role.SENIOR)
            .build();

    updateEmployeeUseCase.execute(dto, model.getId());
    EmployeeModel updatedEmployee = getByIdOrThrowUseCase.execute(model.getId());

    assertEquals(dto.getFullName(), updatedEmployee.getFullName());
  }
}
