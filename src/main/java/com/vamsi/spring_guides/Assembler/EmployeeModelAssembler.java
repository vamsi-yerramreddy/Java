package com.vamsi.spring_guides.Assembler;

import com.vamsi.spring_guides.Controller.EmployeeController;
import com.vamsi.spring_guides.Controller.EmployeeController.*;
import com.vamsi.spring_guides.payroll.Employee;
import com.vamsi.spring_guides.payroll.EmployeeRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> toModel(Employee employee){
        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).one(employee.getEmpId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employee"));
    }
}
