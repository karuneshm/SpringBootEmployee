package com.karunesh.springboot.CRUDDemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.karunesh.springboot.CRUDDemo.entity.Employee;

@Repository
public class EmployeDAOJpaImpl implements EmployeDAO {
	
	private EntityManager entityManager;
	
    @Autowired
	public EmployeDAOJpaImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		Query theQuery = entityManager.createQuery("from Employee");
		List<Employee> employeeList = theQuery.getResultList();
		return employeeList;
	}

	@Override
	public Employee findByID(int theId) {
		Employee employee = entityManager.find(Employee.class, theId);
		return employee;
	}

	@Override
	public void save(Employee theEmployee) {
		Employee dbEmployee = entityManager.merge(theEmployee);
		theEmployee.setId(dbEmployee.getId());

	}

	@Override
	public void deleteByID(int theId) {
		Query theQuery = entityManager.createQuery(
				" delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
				

	}

}
