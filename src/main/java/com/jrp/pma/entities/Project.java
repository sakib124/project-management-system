package com.jrp.pma.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
	@SequenceGenerator(name = "project_seq", allocationSize = 1)
	private long projectId;
	private String name;
	private String stage;
	private String description;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
	@JsonIgnore
	private List<Employee> employees;

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(String name, String stage, String description, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;

	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void addEmployee(Employee emp1) {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		employees.add(emp1);
	}

}
