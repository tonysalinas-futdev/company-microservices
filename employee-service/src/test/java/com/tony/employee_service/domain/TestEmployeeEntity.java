package com.tony.employee_service.domain;

import com.tony.employee_service.domain.entitys.Employee;
import com.tony.employee_service.domain.entitys.Position;
import com.tony.employee_service.domain.entitys.Role;
import com.tony.employee_service.domain.exceptions.EmployeeException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestEmployeeEntity {

  @Test
  void shouldCreateEmployeeSuccessfully() {
    Assertions.assertDoesNotThrow(
        () ->
            new Employee(
                "Dexter Morgan",
                "dexter@gmail.com",
                "4",
                Position.SOFTWARE_ENGINEER,
                Role.JUNIOR,
                BigDecimal.valueOf(1500L),
                true));
  }

  @Test
  void shouldFailToCreateEmployeeWhenReceiveNullName() {
    Assertions.assertThrows(
        EmployeeException.class,
        () ->
            new Employee(
                null,
                "dexter@gmail.com",
                "4",
                Position.SOFTWARE_ENGINEER,
                Role.JUNIOR,
                BigDecimal.valueOf(1500L),
                true));
  }

  @Test
  void shouldFailToCreateEmployeeWhenReceiveBlankName() {
    Assertions.assertThrows(
        EmployeeException.class,
        () ->
            new Employee(
                "",
                "dexter@gmail.com",
                "4",
                Position.SOFTWARE_ENGINEER,
                Role.JUNIOR,
                BigDecimal.valueOf(1500L),
                true));
  }

  @Test
  void shouldFailToCreateEmployeeWhenReceiveNullEmail() {
    Assertions.assertThrows(
        EmployeeException.class,
        () ->
            new Employee(
                "Dexter Morgan",
                null,
                "4",
                Position.SOFTWARE_ENGINEER,
                Role.JUNIOR,
                BigDecimal.valueOf(1500L),
                true));
  }
}
