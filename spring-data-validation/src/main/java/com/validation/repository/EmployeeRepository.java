package com.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.validation.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String>
{
	
}
