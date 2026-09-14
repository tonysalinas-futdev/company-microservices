package com.tony.department_service.domain.entity;

import com.tony.department_service.domain.exceptions.DepartmentException;
import com.tony.department_service.infraestructure.models.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartmentEntity {

    private String id;
    private String name;
    private String leadId;
    private String description;
    private List<EmployeeEntity> employees;


    public DepartmentEntity(String id, String name, String leadId, String description, List<EmployeeEntity> employees){
        if (name == null || name.isBlank()){
            throw new DepartmentException("The name of the department cannot be null or blank");
        }
        if (leadId == null){
            throw new DepartmentException("Every department must have a lead");
        }
        if (description == null || description.isBlank()){
            throw new DepartmentException("The description cannot be null or empty");
        }


        this.id= id == null ? UUID.randomUUID().toString() : id;
        this.name=name;
        this.leadId=leadId;
        this.description=description;
        this.employees=employees == null ? new ArrayList<>() : employees;


    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLeadId() {
        return leadId;
    }

    public String getDescription() {
        return description;
    }

    public List<EmployeeEntity> getEmployees(){return employees;}

    public void addNewEmployee(EmployeeEntity employee){
        if (employees.contains(employee)){
            throw new DepartmentException("This department already contains the  employee: "+ employee);
        }
        employees.add(employee);
    }

    public void removeEmployee(EmployeeEntity employee){
        employees.remove(employee);
    }



}
