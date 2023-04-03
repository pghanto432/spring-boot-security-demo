package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.entity.Employee;
import com.example.springsecuritydemo.error.EmployeeNotFoundException;
import com.example.springsecuritydemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public  Employee addEmployee(@RequestBody Employee emp){
        return employeeService.addEmployee(emp);
    }

    @RequestMapping(value="/hi", method = RequestMethod.GET)
    public String welcome(){
        return "Hi Pradeep...";
    }

    @GetMapping("/fetchAllEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees() {

        return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/fetchById/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {

        Optional<Employee> user = employeeService.getById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            throw new EmployeeNotFoundException("No Employee Found with the Id:= "+id);
        }
    }

}
