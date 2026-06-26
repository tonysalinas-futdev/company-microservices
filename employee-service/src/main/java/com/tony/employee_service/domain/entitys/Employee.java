package com.tony.employee_service.domain.entitys;

import com.tony.employee_service.domain.exceptions.EmployeeException;
import org.javamoney.moneta.Money;

import java.math.BigDecimal;

public class Employee {
    private String fullName;
    private String email;
    private String departmentId;
    private Position position;
    private Role role;
    private Money salary;
    private Boolean isActive;

    public Employee(String fullName, String email, String departmentId, Position position, Role role, BigDecimal salary, Boolean isActive){
        if (fullName==null || fullName.isBlank()){
            throw new EmployeeException("The name of the employee cannot be null or blank");
        }
        if (email==null || email.isBlank()){
            throw new EmployeeException("The name of the employee cannot be null or blank");
        }

        if (position == null){
            throw new EmployeeException("The employee must have a position");
        }

        if (role == null){
            throw new EmployeeException("The employee must have a role");
        }
        this.fullName=fullName;
        this.email=email;
        this.departmentId=departmentId;
        this.position=position;
        this.role=role;
        this.isActive=isActive;
        this.salary=Money.of(salary,"USD");

    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public Position getPosition() {
        return position;
    }

    public Role getRole() {
        return role;
    }

    public Money getSalary() {
        return salary;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setNewSalary(BigDecimal quantity){
        if (! isActive){
            throw new EmployeeException("Cannot change the salary of unactive employee");
        }
        salary=Money.of(quantity,"usd");
    }



    public void changeActivity(Boolean newStatus){
        isActive=newStatus;
    }
     public void changeDepartment(String id){
        if (isActive){
            departmentId=id;
        }
        else {
            throw new EmployeeException("Cannot change the department of unactive employee");
        }

     }

     public void updateRole(Role newRole){
        if (! isActive){
            throw new EmployeeException("Cannot change the role of unactive employee");
        }

     }

     public void updatePosition(Position newPosition){
         if (! isActive){
             throw new EmployeeException("Cannot change the position of unactive employee");
         }
         position=newPosition;


     }
}
