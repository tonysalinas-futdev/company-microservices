package com.tony.department_service.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@ToString
public class CreateDepartmentDTO {
    private final String id;
    @NotNull(message = "The name of the department cannot be null")
    @NotBlank(message = "The name of department cannot be blank")
    private final String name;

    @NotNull(message = "The department must have a description")
    private final String description;
    private final String leadId;

}
