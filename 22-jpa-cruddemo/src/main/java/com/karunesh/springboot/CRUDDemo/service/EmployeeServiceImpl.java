package com.karunesh.springboot.CRUDDemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.karunesh.springboot.CRUDDemo.dao.EmployeDAO;
import com.karunesh.springboot.CRUDDemo.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeDAO employeeDAO;
	
	
	@Autowired
	public EmployeeServiceImpl(@Qualifier("employeDAOJpaImpl") EmployeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	@Transactional
	public Employee findByID(int theId) {
		return employeeDAO.findByID(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeDAO.save(theEmployee);

	}

	@Override
	@Transactional
	public void deleteByID(int theId) {
		employeeDAO.deleteByID(theId);

	}

}
