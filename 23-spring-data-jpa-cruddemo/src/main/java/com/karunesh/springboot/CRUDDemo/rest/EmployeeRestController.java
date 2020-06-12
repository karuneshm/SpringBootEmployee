package com.karunesh.springboot.CRUDDemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.karunesh.springboot.CRUDDemo.entity.Employee;
import com.karunesh.springboot.CRUDDemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService; 

	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
//	@GetMapping("/employees1")
//	public Employee getEmployeeByID(@RequestParam(name="employeeId") int employeeId) {
//		return employeeService.findByID(employeeId);
//	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId) {
		Employee employee = employeeService.findByID(employeeId);
		
		if(employee == null) {
			throw new RuntimeException("The employee with id "+employeeId+ " not found");
		}
		
		return employee;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {
		employee.setId(0);
		employeeService.save(employee);	
		return employee;		
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee) {
		employeeService.save(employee);	
		return employee;	
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteById(@PathVariable int employeeId) {
		Employee employee = employeeService.findByID(employeeId);
		
		if(employee == null) {
			throw new RuntimeException("The employee with id "+employeeId+ " not found");
		}
		
		employeeService.deleteByID(employeeId);
		return "The deleted employee Id is" + employeeId;
	}

	

}
