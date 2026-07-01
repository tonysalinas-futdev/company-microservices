package com.tony.employee_service.domain.event;

import com.tony.employee_service.domain.entitys.Employee;
import java.time.Instant;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class EmployeeUpdatedDomainEvent {
  private final String id;
  private final String fullName;
  private final String email;
  private final String departmentId;
  private final Long timestamp;

  public static EmployeeUpdatedDomainEvent of(Employee employee) {
    return EmployeeUpdatedDomainEvent.builder()
        .id(employee.getId())
        .fullName(employee.getFullName())
        .email(employee.getEmail())
        .departmentId(employee.getDepartmentId())
        .timestamp(Instant.now().toEpochMilli())
        .build();
  }
}
