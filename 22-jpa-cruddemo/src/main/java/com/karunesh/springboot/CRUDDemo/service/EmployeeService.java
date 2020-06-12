package com.karunesh.springboot.CRUDDemo.service;

import java.util.List;

import com.karunesh.springboot.CRUDDemo.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findByID(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteByID(int theId);

}
