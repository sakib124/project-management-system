package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;
import com.jrp.pma.services.EmployeeService;
import com.jrp.pma.services.ProjectService;

@Controller
public class HomeController {

	@Autowired
	ProjectService proService;
	@Autowired
	EmployeeService empService;

	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		Iterable<Project> projects = proService.findAll();
		model.addAttribute("projectsList", projects);
		List<ChartData> projectsStageCount = proService.employeeProjects();
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectsStageCount);
		model.addAttribute("projectsStageList", jsonString);
		List<EmployeeProject> employeesProjectCnt = empService.employeeProjects();
		model.addAttribute("employeesList", employeesProjectCnt);
		return "main/home";
	}
}
