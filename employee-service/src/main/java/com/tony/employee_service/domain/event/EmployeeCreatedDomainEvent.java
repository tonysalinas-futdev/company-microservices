package com.tony.employee_service.domain.event;

import com.tony.employee_service.domain.entitys.Employee;
import com.tony.employee_service.domain.entitys.Position;
import com.tony.employee_service.domain.entitys.Role;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EmployeeCreatedDomainEvent {
  private final String id;
  private final String fullName;
  private final String email;
  private final String departmentId;
  private final Position position;
  private final Role role;
  private final BigDecimal salary;
  private final Long timestamp;

  public static EmployeeCreatedDomainEvent of(Employee employee) {
    return EmployeeCreatedDomainEvent.builder()
        .id(employee.getId())
        .fullName(employee.getFullName())
        .email(employee.getEmail())
        .departmentId(employee.getDepartmentId() == null ? null : employee.getDepartmentId())
        .position(employee.getPosition())
        .role(employee.getRole())
        .salary(BigDecimal.valueOf(employee.getSalary().getNumber().doubleValueExact()))
        .timestamp(Instant.now().toEpochMilli())
        .build();
  }
}
