package com.karunesh.springboot.CRUDDemo.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.karunesh.springboot.CRUDDemo.dao.EmployeeRepository;
import com.karunesh.springboot.CRUDDemo.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	
	@Autowired
	public EmployeeServiceImpl( EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findByID(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		
		Employee employee = null;
		if(result.isPresent())
		{
			employee =  result.get();
		}
		else {
			throw new RuntimeException("did not find employee Id - " +theId);
		 }
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);

	}

	@Override
	public void deleteByID(int theId) {
		employeeRepository.deleteById(theId);

	}

}
