package com.tony.employee_service.application.usecases;

import com.tony.employee_service.infraestructure.event.specificproducer.EmployeeEventsProducer;
import com.tony.employee_service.infraestructure.models.EmployeeModel;
import com.tony.employee_service.infraestructure.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteEmployeeUseCase {
    private final EmployeeRepository repo;
    private final EmployeeEventsProducer producer;
    private final GetByIdOrThrowUseCase getByIdOrThrowUseCase;

    public void execute(Long id){
        EmployeeModel employee=getByIdOrThrowUseCase.execute(id);
        employee.setIsActive(false);
        repo.save(employee);
        log.info("Setted employee: {} as inactive",employee);
        producer.produceEmployeeDeletedEvent(employee);
    }
}
