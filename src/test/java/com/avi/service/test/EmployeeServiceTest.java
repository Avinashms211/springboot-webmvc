package com.avi.service.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.avi.Employee;
import com.avi.repository.EmployeeRepository;
import com.avi.service.EmployeeService;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEmployee() {
        Employee emp = new Employee(1, "Avinash", "Bengaluru", 50000f);
        when(employeeRepository.save(emp)).thenReturn(emp);

        Employee saved = employeeService.saveEmployee(emp);

        assertEquals(emp.getName(), saved.getName());
        verify(employeeRepository, times(1)).save(emp);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = Arrays.asList(
            new Employee(1, "Avinash", "Bengaluru", 50000f),
            new Employee(2, "Vivek", "Pune", 40000f)
        );

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    public void testGetEmployeeById() {
        Employee emp = new Employee(1, "Avinash", "Bengaluru", 50000f);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));

        Employee result = employeeService.getEmployeeById(1);

        assertNotNull(result);
        assertEquals("Avinash", result.getName());
    }

    @Test
    public void testDeleteEmployee() {
        int empId = 1;

        employeeService.deleteEmployee(empId);

        verify(employeeRepository, times(1)).deleteById(empId);
    }
    @Test
    public void testFindEmployeeById() {
        Employee emp = new Employee(1, "Avinash", "Bangalore", 50000f);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(emp));

        Employee result = employeeService.getEmployeeById(1);
        assertEquals(emp, result);
    }
    
}
