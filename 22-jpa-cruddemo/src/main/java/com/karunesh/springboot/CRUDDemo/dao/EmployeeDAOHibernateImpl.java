package com.karunesh.springboot.CRUDDemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.karunesh.springboot.CRUDDemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeDAO {
	
	private EntityManager entity;
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entity) {
		this.entity = entity;
	}



	@Override
	public List<Employee> findAll() {
		Session currentSession = entity.unwrap(Session.class);
		Query<Employee> theQuery = 
				currentSession.createQuery("from Employee", Employee.class);
		
		List<Employee> employeeList = theQuery.getResultList();
		return employeeList;
	}



	@Override
	public Employee findByID(int theId) {
		Session currentSession = entity.unwrap(Session.class);
		Employee employee = currentSession.get(Employee.class,theId);
		return employee;
	}
	
	@Override
	public void save(Employee theEmployee) {
		Session currentSession = entity.unwrap(Session.class);
		currentSession.saveOrUpdate(theEmployee);	
	}



	@Override
	public void deleteByID(int theId) {
		Session currentSession = entity.unwrap(Session.class);
		Query theQuery = currentSession.createQuery(
				"delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
	    theQuery.executeUpdate();
		
	}

}
