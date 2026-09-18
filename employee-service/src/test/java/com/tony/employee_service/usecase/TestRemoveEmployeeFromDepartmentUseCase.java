package com.tony.employee_service.usecase;


import com.tony.employee_service.application.usecases.AssignEmployeeToDepartmentUseCase;
import com.tony.employee_service.application.usecases.GetByIdOrThrowUseCase;
import com.tony.employee_service.application.usecases.RemoveEmployeeFromDepartmentUseCase;
import com.tony.employee_service.domain.entitys.Position;
import com.tony.employee_service.domain.entitys.Role;
import com.tony.employee_service.infraestructure.models.EmployeeModel;
import com.tony.employee_service.infraestructure.repositories.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestRemoveEmployeeFromDepartmentUseCase {

    @Autowired
    private AssignEmployeeToDepartmentUseCase useCase;
    @Autowired private EmployeeRepository repo;

    @Autowired private GetByIdOrThrowUseCase getByIdOrThrowUseCase;

    @Autowired private RemoveEmployeeFromDepartmentUseCase removeEmployeeFromDepartmentUseCase;

    EmployeeModel saveEmployeeInDbForTests() {
        EmployeeModel employee =
                new EmployeeModel(
                        UUID.randomUUID().toString(),
                        "Test Employee",
                        "test@gmail.com",
                        BigDecimal.valueOf(1500.00),
                        "456",
                        Role.SENIOR,
                        Position.SOFTWARE_ENGINEER,
                        null,
                        null,
                        true);
        repo.save(employee);
        return employee;
    }

    @Test
    void shouldRemoveEmployeeFromDepartmentSuccessfully(){

        EmployeeModel employeeModel = saveEmployeeInDbForTests();

        removeEmployeeFromDepartmentUseCase.execute(employeeModel.getId());
        EmployeeModel updatedEmployee= getByIdOrThrowUseCase.execute(employeeModel.getId());

        Assertions.assertNull(updatedEmployee.getDepartmentId());

    }
}
