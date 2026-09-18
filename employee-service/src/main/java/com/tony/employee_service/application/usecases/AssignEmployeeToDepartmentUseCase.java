package com.tony.employee_service.application.usecases;

import com.tony.employee_service.domain.entitys.Employee;
import com.tony.employee_service.domain.event.EmployeeAssignedToDepartmentDomainEvent;
import com.tony.employee_service.domain.exceptions.EmployeeException;
import com.tony.employee_service.infraestructure.event.specificproducer.EmployeeEventsProducer;
import com.tony.employee_service.infraestructure.mapper.EmployeeMapper;
import com.tony.employee_service.infraestructure.models.EmployeeModel;
import com.tony.employee_service.infraestructure.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class AssignEmployeeToDepartmentUseCase {
    private final EmployeeRepository repo;
    private final EmployeeEventsProducer producer;
    private final EmployeeMapper mapper;
    private final GetByIdOrThrowUseCase getByIdOrThrowUseCase;

    public void execute(String employeeId, String departmentId) {
        EmployeeModel employee = getByIdOrThrowUseCase.execute(employeeId);

        if (employee.getDepartmentId() != null) {
            throw new EmployeeException("The employee already have a department");
        }

        employee.setDepartmentId(departmentId);
        Employee domainEntity = mapper.modelToEntity(employee);
        repo.save(employee);
        log.info("Assigned the department with id={} to employee with id={}", departmentId, employeeId);

        EmployeeAssignedToDepartmentDomainEvent domainEvent = EmployeeAssignedToDepartmentDomainEvent.of(domainEntity);

        producer.produceEmployeeAssignedToDepartmentEvent(domainEvent);

    }

}

