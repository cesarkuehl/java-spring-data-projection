package test.cesarkuehl.ProjectionsTest.dto;

import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

public class BranchDTO {
	
	private String name;
	
	private List<EmployeeDTO> employees;
	
	@QueryProjection
	public BranchDTO(String name) {
		this.name = name;
	}
	
	@QueryProjection
	public BranchDTO(String name, List<EmployeeDTO> employees) {
		this.name = name;
		this.employees = employees;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}
	
}
