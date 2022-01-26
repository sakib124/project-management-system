package com.jrp.pma.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.services.EmployeeService;

@RestController
@RequestMapping("/api-employees")
public class EmployeeApiController {
	@Autowired
	EmployeeService empService;

	@GetMapping
	public Iterable<Employee> getEmployees() {
		return empService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Employee> getEmployeeById(@PathVariable Long id) {
		return empService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody Employee employee) {
		return empService.save(employee);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Employee updateEmployee(@RequestBody Employee employee) {
		return empService.save(employee);
	}

	@PatchMapping("/{id}")
	public Employee partialUpdateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		Employee emp = empService.findById(id).get();

		if (employee.getEmail() != null) {
			emp.setEmail(employee.getEmail());
		}

		if (employee.getFirstName() != null) {
			emp.setFirstName(employee.getFirstName());
		}

		if (employee.getLastName() != null) {
			emp.setLastName(employee.getLastName());
		}

		return empService.save(emp);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		try {
		empService.delete(id);
		}
		catch(EmptyResultDataAccessException e) {
			
		}
	}
	
	@GetMapping(params= {"page","size"})
	public Iterable<Employee> getPaginatedEmployees(@RequestParam int page, @RequestParam int size) {
		Pageable pageAndSize = PageRequest.of(page, size);
		
		return empService.findAll(pageAndSize);
	}
	
}
