package com.playground.service.impl;

import com.playground.pojo.Department;
import com.playground.pojo.Employee;
import com.playground.repo.DepartmentRepository;
import com.playground.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public List<Department> getDepartmentByNameAndMinId(String name, int minId) {
        List<Department> departments = departmentRepository.findByNameAndMinId(name, minId);
        for (Department department : departments) {
            department.setEmployeeNames(department.getEmployeeList().stream().map(Employee::getName).toList());
        }
        return departments;
    }

    @Override
    public void updateDepartment(Department department) {
        departmentRepository.update(department);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.delete(id);
    }

}
