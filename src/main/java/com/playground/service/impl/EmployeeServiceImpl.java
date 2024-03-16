package com.playground.service.impl;

import com.playground.repo.DepartmentRepository;
import com.playground.repo.EmployeeRepository;
import com.playground.pojo.Employee;
import com.playground.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAdultEmployee() {
        return employeeRepository.findAdultEmployee();
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteEmployeeUnderAge(int age) {
        employeeRepository.deleteEmployeeUnderAgeById(age);
    }

}
