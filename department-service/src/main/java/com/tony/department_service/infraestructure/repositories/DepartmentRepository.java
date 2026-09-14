package com.tony.department_service.infraestructure.repositories;

import com.tony.department_service.infraestructure.models.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {

    Optional<Department> findByName(String name);

}
