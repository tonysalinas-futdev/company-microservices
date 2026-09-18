package com.tony.employee_service.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AssignEmployeeToDepartmentDTO {
  @NotNull(message = "The id of the employee cannot be null")
  private final String id;

  @NotNull(message = "The departmentd id cannot be null")
  private final String departmentId;
}
