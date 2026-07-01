package com.tony.employee_service.domain.service;

import com.tony.employee_service.domain.exceptions.AlreadyExistsException;
import com.tony.employee_service.infraestructure.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailUniquenessVerificationService {
  private final EmployeeRepository repo;

  public void verify(String email) {
    if (repo.findByEmail(email).isPresent()) {
      throw new AlreadyExistsException("Employee with the email: " + email + " already exists");
    }
  }
}
