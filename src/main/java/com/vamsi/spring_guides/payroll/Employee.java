package com.vamsi.spring_guides.payroll;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Employee {
   @Id @GeneratedValue
   private Long empId;
   private String fistName;
   private String lastName;
   // private String name;
    private String role;

    public Employee(){

    }

    public Employee( String firstName, String lastName, String role) {
        this.fistName=firstName;
        this.lastName=lastName;
        this.role = role;
    }

    public Long getEmpId() {
        return empId;
    }

    public String getName() {
        return this.fistName+" "+this.lastName;
    }

    public String getRole() {
        return role;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public void setName(String name) {
        String [] parts = name.split(" ");
        fistName=parts[0];
        lastName=parts[1];
    }

    public String getFirstName() {
        return this.fistName;
    }

    public String getLastName() {
        return this.lastName;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setFistName(String fistName){
        this.fistName=fistName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
