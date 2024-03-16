package com.playground.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private int age;
    @ManyToOne
    @JoinColumn(name = "deptId")
    private Department department;
}
