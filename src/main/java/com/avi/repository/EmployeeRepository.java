package com.avi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avi.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
