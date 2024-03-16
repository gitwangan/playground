package com.playground.service;

import com.playground.pojo.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void addEmployee(Employee employee);
    List<Employee> findAdultEmployee();
    void updateEmployee(Employee employee);
    void deleteEmployeeUnderAge(int age);
}
