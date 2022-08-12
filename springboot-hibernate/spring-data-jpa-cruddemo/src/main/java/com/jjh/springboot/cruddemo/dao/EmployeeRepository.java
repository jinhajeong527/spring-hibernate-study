package com.jjh.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jjh.springboot.cruddemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
}
