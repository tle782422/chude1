package com.truyentranh.webtruyen.hr.services;


import java.util.List;

import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.hr.models.Employee;

@Service
public interface EmployeeService {
		
//	private final EmployeeRepository employeeRepository;
	
	//Get All Employees
	public List<Employee> findAll();
	
	//Get Employee By Id
	public Employee findById(int id);
	
	//Delete Employee
	public void delete(int id);
	
	//Update Employee
	public Employee save(Employee employee);
	
	//Get Employee by username
	public Employee findByUsername(String un);
	//Get employee by Keyword
	public List<Employee> findByKeyword(String keyword);


}
