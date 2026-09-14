package com.tony.department_service.infraestructure.mappers;

import com.tony.department_service.domain.entity.EmployeeEntity;
import com.tony.department_service.infraestructure.models.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface EmployeeMapper {
    Employee employeeEntityToEmployee(EmployeeEntity entity);
    EmployeeEntity employeeToEmployeeEntity(Employee model);
}
