package com.tony.employee_service.domain.event;

import com.tony.employee_service.domain.entitys.Employee;
import java.time.Instant;
import lombok.*;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EmployeeAssignedToDepartmentDomainEvent {
  private final String id;
  private final String departmentId;
  private final Long timestamp;

  public static EmployeeAssignedToDepartmentDomainEvent of(Employee employee) {
    return EmployeeAssignedToDepartmentDomainEvent.builder()
        .id(employee.getId())
        .departmentId(employee.getDepartmentId())
        .timestamp(Instant.now().toEpochMilli())
        .build();
  }
}
