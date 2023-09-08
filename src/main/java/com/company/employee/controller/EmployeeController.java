package com.company.employee.controller;

import com.company.employee.entity.Employee;
import com.company.employee.model.EmployeeDto;
import com.company.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    private  EmployeeService employeeService ;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmp(@RequestBody EmployeeDto employeeDto)
    {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<?>AllEmployee()
    {
        try {
            return new ResponseEntity<>(employeeService.getAllEmployee(),HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getEmployeeById(@PathVariable long id)
    {
       try {
           return new ResponseEntity<>(employeeService.findEmployeeById(id),HttpStatus.FOUND);
       }
       catch (Exception e)
       {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
    @PutMapping
    public ResponseEntity<?> updateEmployee(@PathVariable long id,@RequestBody EmployeeDto employeeDto)
    {

        return new ResponseEntity<>(employeeService.updateEmployee(id,employeeDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteEmployeeById(@PathVariable long id)
    {
        Boolean deleted=false;
        deleted= employeeService.deleteEmployee(id);
        Map<String,Boolean> response =new HashMap<>();
        response.put("deleted",deleted);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
