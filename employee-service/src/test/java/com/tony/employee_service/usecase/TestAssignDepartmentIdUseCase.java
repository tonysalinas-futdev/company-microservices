package com.tony.employee_service.usecase;

import com.tony.employee_service.application.usecases.AssignEmployeeToDepartmentUseCase;
import com.tony.employee_service.application.usecases.GetByIdOrThrowUseCase;
import com.tony.employee_service.domain.entitys.Position;
import com.tony.employee_service.domain.entitys.Role;
import com.tony.employee_service.domain.exceptions.EmployeeException;
import com.tony.employee_service.infraestructure.models.EmployeeModel;
import com.tony.employee_service.infraestructure.repositories.EmployeeRepository;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestAssignDepartmentIdUseCase {

  @Autowired private AssignEmployeeToDepartmentUseCase useCase;
  @Autowired private EmployeeRepository repo;

  @Autowired private GetByIdOrThrowUseCase getByIdOrThrowUseCase;

  EmployeeModel saveEmployeeInDbForTests() {
    EmployeeModel employee =
        new EmployeeModel(
            UUID.randomUUID().toString(),
            "Test Employee",
            "test@gmail.com",
            BigDecimal.valueOf(1500.00),
            null,
            Role.SENIOR,
            Position.SOFTWARE_ENGINEER,
            null,
            null,
            true);
    repo.save(employee);
    return employee;
  }

  @Test
  void shouldAssignDepartment4ToEmployee() {
    EmployeeModel employee = saveEmployeeInDbForTests();
    useCase.execute(employee.getId(), "4");
    EmployeeModel updatedEmployee = getByIdOrThrowUseCase.execute(employee.getId());

    Assertions.assertEquals("4", updatedEmployee.getDepartmentId());
  }

  @Test
  void shouldFailToAssignDepartmentWhenEmployeeAlreadyHave() {
    EmployeeModel employee =
        new EmployeeModel(
            UUID.randomUUID().toString(),
            "Test Employee",
            "test@gmail.com",
            BigDecimal.valueOf(1500.00),
            "10",
            Role.SENIOR,
            Position.SOFTWARE_ENGINEER,
            null,
            null,
            true);
    repo.save(employee);
    Assertions.assertThrows(EmployeeException.class, () -> useCase.execute(employee.getId(),"5"));
  }
}
