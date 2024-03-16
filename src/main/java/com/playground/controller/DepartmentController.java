package com.playground.controller;

import com.playground.pojo.Department;
import com.playground.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hr/department/")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("add")
    public ResponseEntity<Void> addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("find/name/{name}/minId/{minId}")
    public ResponseEntity<List<Department>> getDepartmentByNameAndMinId(@PathVariable String name, @PathVariable int minId) {
        return new ResponseEntity<>(departmentService.getDepartmentByNameAndMinId(name, minId), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateDepartment(@PathVariable int id, @RequestBody Department department) {
        if (id != 0 && id != department.getId()) {
            throw new RuntimeException("Cannot update: different value of id in path variable and id in request body");
        }
        department.setId(id);
        departmentService.updateDepartment(department);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
