package com.playground.controller;

import com.playground.pojo.Employee;
import com.playground.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hr/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/adults")
    public ResponseEntity<List<Employee>> getAdultEmployee() {
        return new ResponseEntity<>(employeeService.findAdultEmployee(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        if (employee.getId() != 0 && id != employee.getId()) {
            throw new RuntimeException("Cannot update: different value of id in path variable and id in request body");
        }
        employee.setId(id);
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEmployee(@RequestParam int minAge) {
        employeeService.deleteEmployeeUnderAge(minAge);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
