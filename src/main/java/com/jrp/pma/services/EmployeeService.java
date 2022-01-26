package com.jrp.pma.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository empRepo;

	public Employee save(Employee employee) {
		return empRepo.save(employee);

	}

	public Iterable<Employee> findAll() {
		return empRepo.findAll();
	}

	public List<EmployeeProject> employeeProjects() {
		return empRepo.employeeProjects();
	}

	public Optional<Employee> findById(Long id) {
		// TODO Auto-generated method stub
		return empRepo.findById(id);
	}

	public void delete(Long id) {
		empRepo.deleteById(id);
	}
	
	public Iterable<Employee> findAll(Pageable page) {
		return empRepo.findAll(page);
	}
}
