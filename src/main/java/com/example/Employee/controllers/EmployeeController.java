package com.example.Employee.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Employee.exception.ResourceNotFoundException;
import com.example.Employee.models.Employee;
import com.example.Employee.repository.EmployeeRepository;

@RestController
@RequestMapping(value="/api")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping(value="/employee")
	public List<Employee> getAllEmployees(){
		return empRepo.findAll();
	}
	
	@PostMapping(value="/employee")
	public Employee saveEmployee(@Valid @RequestBody Employee employee){
		return empRepo.save(employee);		
	}
	
	@GetMapping(value="/employee/{id}")
	public Employee getById(@PathVariable(value="id") Integer id){
		Optional<Employee> emp = empRepo.findById(id);
		if(emp.isPresent()){
			return emp.get();
		}else{
			throw new ResourceNotFoundException("Employee", "id", id);
		}
		
		}
	
	@PutMapping(value="/employee/{id}")
	public Employee updateEmployee(@Valid @RequestBody Employee employee, @PathVariable(value="id") Integer id){
		Optional<Employee> emp = empRepo.findById(id);
		Employee empUpdate;
		if(emp.isPresent()){
			empUpdate = emp.get();
		}else{
			throw new ResourceNotFoundException("Employee", "id", id);
		}
		empUpdate.setDepartment(employee.getDepartment());
		empUpdate.setDesignation(employee.getDesignation());
		empUpdate.setSalary(employee.getSalary());
		
		Employee empUpdated = empRepo.save(empUpdate);
		return empUpdated;
	}
	
	@DeleteMapping(value="/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id){
		Optional<Employee> emp = empRepo.findById(id);
		Employee empUpdate;
		if(emp.isPresent()){
			empUpdate = emp.get();
		}else{
			throw new ResourceNotFoundException("Employee", "id", id);
		}
		
		empRepo.delete(empUpdate);
		return ResponseEntity.ok().build();
	}
}
