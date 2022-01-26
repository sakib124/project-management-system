package com.jrp.pma.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@RestController
@RequestMapping("/api-projects")
public class ProjectApiController {
	@Autowired
	ProjectService proService;

	@GetMapping
	public Iterable<Project> getProjects() {
		return proService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Project> getProjectById(@PathVariable Long id) {
		return proService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Project createProject(@RequestBody Project project) {
		return proService.save(project);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Project updateProject(@RequestBody Project project) {
		return proService.save(project);
	}

	@PatchMapping("/{id}")
	public Project partialUpdateProject(@PathVariable Long id, @RequestBody Project project) {
		Project proj = proService.findById(id).get();

		if (project.getName() != null) {
			proj.setName(project.getName());
		}

		if (project.getStage() != null) {
			proj.setStage(project.getStage());
		}

		if (project.getDescription() != null) {
			proj.setDescription(project.getDescription());
		}

		return proService.save(proj);
	}

	@DeleteMapping("/{id}")
	public void deleteProject(@PathVariable Long id) {
		try {
			proService.delete(id);
		} catch (EmptyResultDataAccessException e) {

		}
	}
}
