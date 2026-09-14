package com.tony.department_service.application.usecases;


import com.tony.department_service.infraestructure.models.Department;
import com.tony.department_service.infraestructure.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllDepartments {
    private final DepartmentRepository repo;

    public List<Department> execute(){
        return repo.findAll();
    }
}
