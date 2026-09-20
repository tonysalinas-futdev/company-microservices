package com.tony.employee_service.application.usecases;

import com.tony.employee_service.domain.event.EmployeeRemovedFromDepartmentDomainEvent;
import com.tony.employee_service.infraestructure.event.specificproducer.EmployeeEventsProducer;
import com.tony.employee_service.infraestructure.mapper.EmployeeMapper;
import com.tony.employee_service.infraestructure.models.EmployeeModel;
import com.tony.employee_service.infraestructure.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class RemoveEmployeeFromDepartmentUseCase {
  private final EmployeeRepository repo;
  private final EmployeeEventsProducer producer;
  private final EmployeeMapper mapper;
  private final GetByIdOrThrowUseCase getByIdOrThrowUseCase;

  public void execute(String employeeId) {

    EmployeeModel employee = getByIdOrThrowUseCase.execute(employeeId);
    if (employee.getDepartmentId() == null) {
      return;
    }
    EmployeeRemovedFromDepartmentDomainEvent domainEvent =
        new EmployeeRemovedFromDepartmentDomainEvent(
            employeeId, employee.getDepartmentId(), Instant.now().toEpochMilli());
    employee.setDepartmentId(null);
    log.info(
        "Removed the employee with id={} , from department with id = {}",
        employee.getId(),
        employee.getDepartmentId());
    repo.save(employee);
    producer.produceEmployeeRemovedFromDepartmentEvent(domainEvent);
  }
}
