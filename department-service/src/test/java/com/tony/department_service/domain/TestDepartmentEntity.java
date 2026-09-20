package com.tony.department_service.domain;


import com.tony.department_service.domain.entity.DepartmentEntity;
import com.tony.department_service.domain.entity.EmployeeEntity;
import com.tony.department_service.domain.exceptions.DepartmentException;
import com.tony.department_service.infraestructure.models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDepartmentEntity {

    public DepartmentEntity getDepartmentForTest(){
        return new DepartmentEntity(null, "DevOps","4345","The department of DevOpds",null);

    }
    @Test
    void shouldCreateDepartmentSuccessfully(){
        Assertions.assertDoesNotThrow(()-> new DepartmentEntity(null, "DevOps","4345","The department of DevOpds",null));
    }

    @Test
    void shouldFailWhenCreateDepartmentWithoutName(){
        Assertions.assertThrows(DepartmentException.class,()-> new DepartmentEntity(null, null,"4345","The department of DevOpds",null));

    }
    @Test
    void shouldFailWhenCreateDepartmentWithoutDescription(){
        Assertions.assertThrows(DepartmentException.class,()-> new DepartmentEntity(null, "DevOps","4345",null,null));

    }

    @Test
    void shouldFailWhenCreateDepartmentWithEmptyDescription(){
        Assertions.assertThrows(DepartmentException.class,()-> new DepartmentEntity(null, "DevOps","4345","  ",null));

    }


    @Test
    void shouldAddNewEmployeeSuccessfully(){
        DepartmentEntity entity= getDepartmentForTest();
        entity.addNewEmployee(new EmployeeEntity("234235"));
        Assertions.assertEquals(1,entity.getEmployees().size());
    }

    @Test
    void shouldThrowDepartmentExceptionWhenAddExistentEmployee(){
        DepartmentEntity entity= getDepartmentForTest();
        entity.addNewEmployee(new EmployeeEntity("234235"));
        Assertions.assertThrows(DepartmentException.class,()-> entity.addNewEmployee(new EmployeeEntity("234235")));
    }
}
