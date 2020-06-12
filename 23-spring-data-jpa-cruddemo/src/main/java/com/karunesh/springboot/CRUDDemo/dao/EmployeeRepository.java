package com.karunesh.springboot.CRUDDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karunesh.springboot.CRUDDemo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
