package com.vamsi.spring_guides.payroll;

 public class EmployeeNotFoundException extends  RuntimeException{

    public EmployeeNotFoundException(Long id) {
        super("Could not find Employee" + id);
    }

}
