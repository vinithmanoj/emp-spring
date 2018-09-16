package com.example.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Employee.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
