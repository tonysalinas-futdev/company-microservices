package com.tony.employee_service.domain.event;

import com.tony.employee_service.domain.entitys.Employee;
import lombok.*;

import java.time.Instant;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EmployeeRemovedFromDepartmentDomainEvent {
  private final String id;
  private final String departmentId;
  private final Long timestamp;


  public static EmployeeRemovedFromDepartmentDomainEvent of(Employee employee) {
        return EmployeeRemovedFromDepartmentDomainEvent.builder()
                .id(employee.getId())
                .departmentId(employee.getDepartmentId())
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }
}
