package com.tony.department_service.application.usecases;


import com.tony.department_service.application.dto.UpdateDepartmentDTO;
import com.tony.department_service.domain.entity.DepartmentEntity;
import com.tony.department_service.domain.service.DepartmentNameUniquenessVerificationService;
import com.tony.department_service.infraestructure.mappers.DepartmentMapper;
import com.tony.department_service.infraestructure.models.Department;
import com.tony.department_service.infraestructure.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateDepartmentUseCase {
    private final DepartmentRepository repo;
    private final DepartmentMapper mapper;
    private final GetDepartmentByIdOrThrowUseCase getDepartmentByIdOrThrowUseCase;
    private final DepartmentNameUniquenessVerificationService departmentNameUniquenessVerificationService;

    public Department execute(UpdateDepartmentDTO dto, String departmentId){
        if (dto.getName() != null){
            departmentNameUniquenessVerificationService.verify(dto.getName());
        }
        Department department = getDepartmentByIdOrThrowUseCase.execute(departmentId);
        Department updatedDepartment=mapper.updateDepartmentDTOToModel(dto,department);
        mapper.modelToDomainEntity(updatedDepartment);
        repo.save(updatedDepartment);
        log.info("Updated department with id: {}, successfully, deparment: {}", departmentId, department);
        return department;


    }
}
