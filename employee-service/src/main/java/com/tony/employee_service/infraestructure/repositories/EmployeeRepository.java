package com.tony.employee_service.infraestructure.repositories;

import com.tony.employee_service.infraestructure.models.EmployeeModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, String> {
  Optional<EmployeeModel> findByEmail(String email);

  @Query("SELECT e FROM EmployeeModel e WHERE e.salary BETWEEN :minSalary AND :maxSalary")
  List<EmployeeModel> filterBySalary(Integer minSalary, Integer maxSalary);
}
