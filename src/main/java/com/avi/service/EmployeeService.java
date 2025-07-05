package com.avi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avi.Employee;
import com.avi.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee emp) {
	    return employeeRepository.save(emp);
	}

	public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
