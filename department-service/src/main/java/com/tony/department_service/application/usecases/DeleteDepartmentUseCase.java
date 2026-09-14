package com.tony.department_service.application.usecases;


import com.tony.department_service.domain.exceptions.DepartmentException;
import com.tony.department_service.infraestructure.models.Department;
import com.tony.department_service.infraestructure.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor

public class DeleteDepartmentUseCase {
    private final DepartmentRepository repo;
    private final GetDepartmentByIdOrThrowUseCase getDepartmentByIdOrThrowUseCase;

    @Transactional
    public void execute(String departmentId){
        Department department= getDepartmentByIdOrThrowUseCase.execute(departmentId);
        if (! department.getEmployees().isEmpty()){
            throw new DepartmentException("Cannot delete department with assigned employees");
        }

        repo.delete(department);
        log.info("Deleted the department: {}", department);

    }
}
