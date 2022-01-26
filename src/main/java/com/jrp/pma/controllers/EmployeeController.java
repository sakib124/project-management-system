package com.jrp.pma.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public String displayEmployees(Model model) {
		Iterable<Employee> employees = empService.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		Employee anEmployee = new Employee();
		model.addAttribute("employee", anEmployee);
		return "employees/new-employee";
	}

	@PostMapping("/save")
	public String createEmployeeForm(Employee employee, Model model) {
		empService.save(employee);
		return "redirect:/employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeForm(@RequestParam long id, Model model) {
		Optional<Employee> theEmp = empService.findById(id);
		model.addAttribute("employee", theEmp);
		return "employees/new-employee";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam long id, Model model) {
		empService.delete(id);
		return "redirect:/employees";
	}
}
