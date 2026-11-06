package com.validation.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.validation.entity.Employee;
import com.validation.repository.EmployeeRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{
	
	private final EmployeeRepository employeeRepository;

	public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@PostMapping
	public Object insertData(@Valid @RequestBody Employee student)
	{
		employeeRepository.save(student);
		return "Data inserted Successfully";
	}
	
	@GetMapping("/{id}")
	public Optional<Employee> fetchData(@PathVariable String empid)
	{
		return employeeRepository.findById(empid);
	}
}
