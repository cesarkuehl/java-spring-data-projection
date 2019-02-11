package test.cesarkuehl.ProjectionsTest.dto;

import com.querydsl.core.annotations.QueryProjection;

public class EmployeeDTO {
	
	private String name;
	
	private BranchDTO branch;
	
	@QueryProjection
	public EmployeeDTO(String name) {
		this.name = name;
	}
	
	@QueryProjection
	public EmployeeDTO(String name, BranchDTO branch) {
		this.name = name;
		this.branch = branch;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BranchDTO getBranch() {
		return branch;
	}

	public void setBranch(BranchDTO branch) {
		this.branch = branch;
	}
	
}
