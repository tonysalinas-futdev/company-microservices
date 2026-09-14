package com.tony.department_service.infraestructure.mappers;

import com.tony.department_service.application.dto.CreateDepartmentDTO;
import com.tony.department_service.application.dto.UpdateDepartmentDTO;
import com.tony.department_service.domain.entity.DepartmentEntity;
import com.tony.department_service.infraestructure.models.Department;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface DepartmentMapper extends EmployeeMapper {
    DepartmentEntity modelToDomainEntity(Department model);

    Department entityToModel(DepartmentEntity entity);

    DepartmentEntity createDepartmentDTOToEntity(CreateDepartmentDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Department updateDepartmentDTOToModel(UpdateDepartmentDTO dto, @MappingTarget Department model);

}
