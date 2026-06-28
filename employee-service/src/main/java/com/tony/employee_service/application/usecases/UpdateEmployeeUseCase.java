package com.tony.employee_service.application.usecases;

import com.tony.employee_service.domain.entitys.Employee;
import com.tony.employee_service.domain.event.EmployeeUpdatedDomainEvent;
import com.tony.employee_service.domain.service.EmailUniquenessVerificationService;
import com.tony.employee_service.application.dto.UpdateEmployeeDTO;
import com.tony.employee_service.infraestructure.event.specificproducer.EmployeeEventsProducer;
import com.tony.employee_service.infraestructure.mapper.EmployeeMapper;
import com.tony.employee_service.infraestructure.models.EmployeeModel;
import com.tony.employee_service.infraestructure.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UpdateEmployeeUseCase {
    private final EmailUniquenessVerificationService emailUniquenessVerificationServiceVerificationService;
    private final EmployeeRepository repo;
    private final GetByIdOrThrowUseCase getByIdOrThrowUseCase;
    private final EmployeeMapper mapper;
    private final EmployeeEventsProducer producer;

    public EmployeeModel execute(UpdateEmployeeDTO dto, String id){
        if (dto.getEmail() != null){
            emailUniquenessVerificationServiceVerificationService.verify(dto.getEmail());
        }
        EmployeeModel employee=getByIdOrThrowUseCase.execute(id);
        EmployeeModel updatedEmployee=mapper.updateDTOToModel(dto,employee);

        Employee entity=mapper.modelToEntity(updatedEmployee);
        repo.saveAndFlush(updatedEmployee);
        log.info("Sucessfully updated employee: {}", updatedEmployee);
        EmployeeUpdatedDomainEvent domainEvent=EmployeeUpdatedDomainEvent.of(entity);
        producer.produceEmployeeUpdatedEvent(domainEvent);

        return updatedEmployee;


    }
}
