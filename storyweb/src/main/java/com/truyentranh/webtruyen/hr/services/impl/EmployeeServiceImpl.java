package com.truyentranh.webtruyen.hr.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truyentranh.webtruyen.hr.models.Employee;
import com.truyentranh.webtruyen.hr.repositories.EmployeeRepository;
import com.truyentranh.webtruyen.hr.services.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int id) {
		return employeeRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(int id) {
		employeeRepository.deleteById(id);
		
	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee findByUsername(String un) {
		return employeeRepository.findByUsername(un);
	}

	@Override
	public List<Employee> findByKeyword(String keyword) {
		return employeeRepository.findByKeyword(keyword);
	}

}
