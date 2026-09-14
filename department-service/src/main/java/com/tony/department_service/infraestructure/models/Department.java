
package com.tony.department_service.infraestructure.models;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "departments")
public class Department {
    @Id private  String id;
    @Indexed
    private String name;
    private String description;
    private String leadId;
    private List<com.tony.department_service.infraestructure.models.Employee> employees;

}
