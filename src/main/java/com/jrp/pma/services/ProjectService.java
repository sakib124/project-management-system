package com.jrp.pma.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.dto.TimeChartData;
import com.jrp.pma.entities.Employee;
import com.jrp.pma.entities.Project;

@Service
public class ProjectService {

@Autowired
ProjectRepository proRepo;

public Project save(Project project) {
	return proRepo.save(project);
	
}

public Iterable<Project> findAll() {
	return proRepo.findAll();
}

public List<ChartData> employeeProjects() {
return proRepo.employeeProjects();
}

public List<TimeChartData> getTimelinData() {
	return proRepo.getTimelineData();
}


public void delete(Long id) {
	// TODO Auto-generated method stub
	proRepo.deleteById(id);
}

public Optional<Project> findById(Long id) {
	// TODO Auto-generated method stub
	return proRepo.findById(id);
}
}
