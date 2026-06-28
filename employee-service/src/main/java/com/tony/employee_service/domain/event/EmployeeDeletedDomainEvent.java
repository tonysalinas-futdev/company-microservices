package com.tony.employee_service.domain.event;

import com.tony.employee_service.domain.entitys.Employee;
import lombok.*;

import java.time.Instant;

@RequiredArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EmployeeDeletedDomainEvent {
    private final String id;
    private final Long timestamp;

    public static EmployeeDeletedDomainEvent of(String id){
        return EmployeeDeletedDomainEvent.builder()
                .id(id)
                .timestamp(Instant.now().toEpochMilli())
                .build();
    }
}
