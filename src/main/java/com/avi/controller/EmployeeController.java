package com.avi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avi.Employee;
import com.avi.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;
	
	
	//------ Show the form  ------
	@GetMapping("/")
	public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee_form";
    }
	
	
	//------ Handle form submission -------
	
	@PostMapping("/submit")
    public String submitForm(@ModelAttribute("employee") Employee emp) {
        employeeService.saveEmployee(emp);
        System.out.println("Employee Saved! Redirecting to dashboard...");
        return "redirect:/dashboard";
    }
	
	@GetMapping("/employees")
    public String showAllEmployees(Model model) {
        model.addAttribute("list", employeeService.getAllEmployees());
        return "employee_list";
    }
	
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }
    
    @GetMapping("/find")
    public String findEmployee(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            Employee emp = employeeService.getEmployeeById(id);
            if (emp != null) {
                model.addAttribute("emp", emp);
                model.addAttribute("success", "✅ Employee with ID " + id + " found.");
            } else {
                model.addAttribute("error", "❌ Employee with ID " + id + " not found.");
            }
        }
        return "find_employee";
    }

    @GetMapping("/delete")
    public String showDeletePage() {
        return "delete_employee";
    }

    @PostMapping("/delete")
    public String deleteById(@RequestParam("id") int id, Model model) {
        Employee emp = employeeService.getEmployeeById(id);
        if (emp != null) {
            employeeService.deleteEmployee(id);
            model.addAttribute("success", "✅ Employee with ID " + id + " deleted successfully.");
        } else {
            model.addAttribute("error", "❌ Employee with ID " + id + " not found.");
        }
        return "delete_employee";
    }

    @GetMapping("/update")
    public String showUpdateIdForm() {
        return "update_employee_id";
    }

    @PostMapping("/update/form")
    public String loadUpdateForm(@RequestParam("id") int id, Model model) {
        Employee emp = employeeService.getEmployeeById(id);
        if (emp != null) {
            model.addAttribute("employee", emp);
            return "update_employee";
        } else {
            model.addAttribute("error", "❌ Employee with ID " + id + " not found.");
            return "update_employee_id";
        }
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") Employee emp, Model model) {
        employeeService.saveEmployee(emp);
        model.addAttribute("success", "✅ Employee with ID " + emp.getId() + " updated successfully.");
        return "update_employee_id";
    }
}