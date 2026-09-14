package com.tony.department_service.application.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateDepartmentDTO {
    private final String name;
    private final String description;

}
