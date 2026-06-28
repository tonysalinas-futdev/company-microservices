package com.tony.employee_service.application.dto;

import com.tony.employee_service.domain.entitys.Position;
import com.tony.employee_service.domain.entitys.Role;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@RequiredArgsConstructor
public class UpdateEmployeeDTO {
    private final String fullName;
    @Email(message = "Error in the email")
    private final String email;
    private final String departmentId;
    private final Position position;
    private final Role role;
    private final BigDecimal salary;
}
