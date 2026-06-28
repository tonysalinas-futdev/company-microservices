package com.tony.employee_service.usecase;

import com.tony.employee_service.application.usecases.CreateEmployeeUseCase;
import com.tony.employee_service.domain.entitys.Position;
import com.tony.employee_service.domain.entitys.Role;
import com.tony.employee_service.domain.exceptions.AlreadyExistsException;
import com.tony.employee_service.application.dto.CreateEmployeeDTO;
import com.tony.employee_service.infraestructure.event.producer.KafkaProducer;
import com.tony.employee_service.infraestructure.models.EmployeeModel;
import com.tony.employee_service.infraestructure.repositories.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;

@SpringBootTest
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class TestCreateEmployeeUseCase {

     @Autowired
    private CreateEmployeeUseCase useCase;

     @MockitoBean
     private KafkaProducer producer;

     @Autowired
     private EmployeeRepository repo;

     CreateEmployeeDTO getCreateEmployeeDTOForTest(){
         return CreateEmployeeDTO.builder()
                 .fullName("Employee")
                 .email("employee@gmail.com")
                 .departmentId("4")
                 .salary(BigDecimal.valueOf(300L))
                 .role(Role.JUNIOR)
                 .position(Position.SOFTWARE_ENGINEER)

                 .build();
     }
    @Test
    void shouldExecutingSuccessfully(){
        CreateEmployeeDTO dto = CreateEmployeeDTO.builder()
                .fullName("Juan Carlos Chao")
                .email("carlos@gmail.com")
                .departmentId("4")
                .salary(BigDecimal.valueOf(300L))
                .role(Role.JUNIOR)
                .position(Position.SOFTWARE_ENGINEER)

                .build();
        EmployeeModel result=useCase.execute(dto);

        verify(producer, times(1)).send(any(),any());
        Assertions.assertEquals("Juan Carlos Chao", result.getFullName());
        Assertions.assertEquals("carlos@gmail.com", result.getEmail());
        Assertions.assertEquals("Juan Carlos Chao", result.getFullName());


    }

    @Test
    void shouldFailToCreateEmployeeWithExistingEmail(){
         useCase.execute(getCreateEmployeeDTOForTest());

         assertThrows(AlreadyExistsException.class, ()-> useCase.execute(getCreateEmployeeDTOForTest()));
    }


}
