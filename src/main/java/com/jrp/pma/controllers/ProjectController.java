package com.jrp.pma.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.dto.TimeChartData;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired
	ProjectService proService;
	
	@Autowired
	EmployeeService empService;
	
	@GetMapping
	public String displayProjects(Model model) {
		Iterable<Project> projects = proService.findAll();
		model.addAttribute("projects",projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayProjectForm(Model model) {
		Project aProject = new Project();
		Iterable<Employee> employees = empService.findAll();
		model.addAttribute("project", aProject);
		model.addAttribute("allEmployees",employees);
		return "projects/new-project";
	}

	@PostMapping("/save")
	public String createProjectForm(Project project, Model model) {
		proService.save(project);
		
//		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
//		
//		for(Employee emp:chosenEmployees) {
//			emp.setProject(project);
//			empRepo.save(emp);
//		}
		
		return "redirect:/projects";
	}
	
	@GetMapping("/update")
	public String displayEmployeeForm(@RequestParam long id, Model model) {
		Optional<Project> theProj = proService.findById(id);
		Iterable<Employee> employees = empService.findAll();
		model.addAttribute("project", theProj);
		model.addAttribute("allEmployees",employees);
		return "projects/new-project";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam long id, Model model) {
		proService.delete(id);
		return "redirect:/projects";
	}
	
	@GetMapping("/timeline")
	public String displayProjectsTimeline(Model model) throws JsonProcessingException {
		Iterable<Project> projects = proService.findAll();
		model.addAttribute("projectsList", projects);
		List<TimeChartData> projectsTimeData = proService.getTimelinData();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonStringTime = objectMapper.writeValueAsString(projectsTimeData);
		model.addAttribute("projectTimeList", jsonStringTime);
		return "projects/timeline";
	}
	}

