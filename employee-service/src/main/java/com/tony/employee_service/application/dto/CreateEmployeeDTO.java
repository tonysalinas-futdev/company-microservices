package com.tony.employee_service.application.dto;

import com.tony.employee_service.domain.entitys.Position;
import com.tony.employee_service.domain.entitys.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
public class CreateEmployeeDTO {
  @NotNull(message = "The name of the employee cannot be null")
  @NotBlank(message = "The name of the employee cannot be blank")
  private final String fullName;

  @Email(message = "Error in the email")
  private final String email;

  private final String departmentId;

  @NotNull(message = "The position of the employee cannot be null")
  private final Position position;

  @NotNull(message = "The role of the employee cannot be null")
  private final Role role;

  @NotNull(message = "The salary of the employee cannot be null")
  private final BigDecimal salary;
}
