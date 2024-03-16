package com.playground.repo;

import com.playground.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("select e from Employee e where e.age >= 18")
    List<Employee> findAdultEmployee();

    @Modifying
    @Query("delete from Employee e where e.age < :age")
    void deleteEmployeeUnderAgeById(@Param("age") int age);

}
