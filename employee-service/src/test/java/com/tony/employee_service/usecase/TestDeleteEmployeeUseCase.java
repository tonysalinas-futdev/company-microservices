package com.tony.employee_service.usecase;

import static org.mockito.Mockito.*;

import com.tony.employee_service.application.dto.CreateEmployeeDTO;
import com.tony.employee_service.application.usecases.CreateEmployeeUseCase;
import com.tony.employee_service.application.usecases.DeleteEmployeeUseCase;
import com.tony.employee_service.application.usecases.GetByIdOrThrowUseCase;
import com.tony.employee_service.domain.entitys.Position;
import com.tony.employee_service.domain.entitys.Role;
import com.tony.employee_service.infraestructure.models.EmployeeModel;
import com.tony.employee_service.infraestructure.repositories.EmployeeRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestDeleteEmployeeUseCase {
  @Autowired private CreateEmployeeUseCase createEmployeeUseCase;

  @Autowired private DeleteEmployeeUseCase deleteEmployeeUseCase;

  @Autowired private GetByIdOrThrowUseCase getByIdOrThrowUseCase;

  @Autowired private EmployeeRepository repo;

  CreateEmployeeDTO getCreateEmployeeDTOForTest() {
    return CreateEmployeeDTO.builder()
        .fullName("Employee")
        .email("employee@gmail.com")
        .departmentId("4")
        .salary(BigDecimal.valueOf(300L))
        .role(Role.JUNIOR)
        .position(Position.SOFTWARE_ENGINEER)
        .build();
  }

  @Test
  void shouldDeleteEmployeeSuccessfully() {
    EmployeeModel model = createEmployeeUseCase.execute(getCreateEmployeeDTOForTest());

    deleteEmployeeUseCase.execute(model.getId());

    EmployeeModel unactiveEmployee = getByIdOrThrowUseCase.execute(model.getId());

    Assertions.assertFalse(unactiveEmployee.getIsActive());
  }
}
