package com.tony.department_service.application.usecases;


import com.tony.department_service.application.dto.CreateDepartmentDTO;
import com.tony.department_service.domain.entity.DepartmentEntity;
import com.tony.department_service.domain.service.DepartmentNameUniquenessVerificationService;
import com.tony.department_service.infraestructure.mappers.DepartmentMapper;
import com.tony.department_service.infraestructure.models.Department;
import com.tony.department_service.infraestructure.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Slf4j
@Service
public class CreateDepartmentUseCase {
    private final DepartmentRepository repo;
    private final DepartmentMapper mapper;
    private final DepartmentNameUniquenessVerificationService departmentNameUniquenessVerificationService;

    @Transactional
    public Department execute(CreateDepartmentDTO dto){
        departmentNameUniquenessVerificationService.verify(dto.getName());
        DepartmentEntity entity= mapper.createDepartmentDTOToEntity(dto);
        Department model=mapper.entityToModel(entity);
        repo.save(model);
        log.info("Successfully created the department: {}", model);
        return model;

    }
}
