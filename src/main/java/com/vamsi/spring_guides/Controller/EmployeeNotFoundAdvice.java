package com.vamsi.spring_guides.Controller;

import com.vamsi.spring_guides.payroll.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class EmployeeNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)

    String employeeNotFoundHandler(EmployeeNotFoundException ex ){
        return ex.getMessage();
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleExceptions(Exception ex){
        return ex.getMessage();
    }
}


