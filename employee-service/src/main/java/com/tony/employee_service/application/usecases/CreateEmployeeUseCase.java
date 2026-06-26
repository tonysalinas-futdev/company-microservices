package com.tony.employee_service.application.usecases;

import com.tony.employee_service.domain.EmailUniquenessVerificationService;
import com.tony.employee_service.domain.entitys.Employee;
import com.tony.employee_service.infraestructure.dto.CreateEmployeeDTO;
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
public class CreateEmployeeUseCase {
    private final EmployeeRepository repo;
    private final EmployeeEventsProducer producer;
    private final EmployeeMapper mapper;
    private final EmailUniquenessVerificationService verificationService;

    public EmployeeModel execute(CreateEmployeeDTO dto){
        verificationService.verify(dto.getEmail());
        Employee domainEntity=mapper.createEmployeeDtoToEntity(dto);

        EmployeeModel model=mapper.entityToModel(domainEntity);
        model.setIsActive(true);
        model.setSalary(dto.getSalary());
        repo.saveAndFlush(model);
        log.info("Successfully created employee: {}", model);
        producer.produceEmployeeCreatedEvent(model);

        return model;
    }
}
