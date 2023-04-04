package com.vamsi.spring_guides.Controller;

import com.vamsi.spring_guides.Assembler.EmployeeModelAssembler;
import com.vamsi.spring_guides.payroll.Employee;
import  com.vamsi.spring_guides.payroll.EmployeeNotFoundException;
import com.vamsi.spring_guides.payroll.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class EmployeeController {
    private final EmployeeRepository repository;
    private final EmployeeModelAssembler assembler;



    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler){
        this.repository=repository;
        this.assembler=assembler;

    }

   /* @GetMapping("/employee")
    public List<Employee> all(){
        return repository.findAll();
    }*/
    //converting the Resource to Collections of Entities.
   /*@GetMapping("/employee")
   public CollectionModel<EntityModel<Employee>> all() {

       List<EntityModel<Employee>> employees = repository.findAll().stream()
               .map(employee -> EntityModel.of(employee,
                       linkTo(methodOn(EmployeeController.class).one(employee.getEmpId())).withSelfRel(),
                       linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
               .collect(Collectors.toList());

       return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
   }*/

   //converting the above using Java 8
   @GetMapping("/employee")
   public CollectionModel<EntityModel<Employee>> all(){
        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(employees,linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {

        EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    /*@GetMapping("/employee/{id}")
    Employee one(@PathVariable Long id){
        return repository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }*/
    @GetMapping("/employee/{id}")
   public EntityModel<Employee> one (@PathVariable Long id) {
        Employee employee = repository.findById(id)//
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        return assembler.toModel(employee);

        /*EntityModel.of(employee, //
                linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));*/
    }
   /* @PostMapping("/employee")
    Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }*/

    @PostMapping("/employee/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(()->{
                    newEmployee.setEmpId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employee/{id}")
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }


}

