package com.tony.department_service.infraestructure.models;

import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    private String id;
    private String name;

}
