package com.tony.employee_service.domain.event;

import java.time.Instant;
import lombok.*;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EmployeeDeletedDomainEvent {
  private final String id;
  private final Long timestamp;

  public static EmployeeDeletedDomainEvent of(String id) {
    return EmployeeDeletedDomainEvent.builder()
        .id(id)
        .timestamp(Instant.now().toEpochMilli())
        .build();
  }
}
