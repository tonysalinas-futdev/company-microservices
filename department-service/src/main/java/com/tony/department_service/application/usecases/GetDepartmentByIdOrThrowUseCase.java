package com.tony.department_service.application.usecases;

import com.tony.department_service.domain.exceptions.NotFoundException;
import com.tony.department_service.infraestructure.models.Department;
import com.tony.department_service.infraestructure.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetDepartmentByIdOrThrowUseCase {
    private final DepartmentRepository repo;

    public Department execute(String id){
        return repo.findById(id).orElseThrow(()-> new NotFoundException("Department with id: " + id + " not found"));
    }
}
