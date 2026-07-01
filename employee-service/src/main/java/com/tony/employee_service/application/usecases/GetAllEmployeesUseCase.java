package com.tony.employee_service.application.usecases;

import com.tony.employee_service.infraestructure.models.EmployeeModel;
import com.tony.employee_service.infraestructure.repositories.EmployeeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetAllEmployeesUseCase {
  private final EmployeeRepository repo;

  public List<EmployeeModel> execute() {
    return repo.findAll();
  }
}
