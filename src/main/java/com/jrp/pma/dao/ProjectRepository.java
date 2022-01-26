package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.dto.TimeChartData;
import com.jrp.pma.entities.Project;

@RepositoryRestResource(collectionResourceRel = "apiprojects", path = "apiprojects")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery=true, value="SELECT p.stage as label, COUNT(*) as value FROM project p "
			+ "GROUP BY p.stage")
	public List<ChartData> employeeProjects(); 
	
	@Query(nativeQuery=true, value="SELECT p.name, p.start_date as startDate, p.end_date as endDate FROM project p"
			+ " WHERE p.start_date IS NOT NULL")
	public List<TimeChartData> getTimelineData(); 
}
