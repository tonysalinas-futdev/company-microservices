package com.tony.department_service.domain.service;

import com.tony.department_service.domain.exceptions.DepartmentException;
import com.tony.department_service.infraestructure.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentNameUniquenessVerificationService {
    private final DepartmentRepository repo;

    public void verify(String name){
        if (repo.findByName(name).isPresent()){
            throw new DepartmentException("Department with name: " + name + "already exists");
        }
    }
}
