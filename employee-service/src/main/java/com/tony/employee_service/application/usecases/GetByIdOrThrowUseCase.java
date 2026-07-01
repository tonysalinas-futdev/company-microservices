package com.tony.employee_service.application.usecases;

import com.tony.employee_service.domain.exceptions.NotFoundException;
import com.tony.employee_service.infraestructure.models.EmployeeModel;
import com.tony.employee_service.infraestructure.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetByIdOrThrowUseCase {
  private final EmployeeRepository repo;

  public EmployeeModel execute(String id) {
    return repo.findById(id)
        .orElseThrow(() -> new NotFoundException("Employee with id:" + id + " not found"));
  }
}
