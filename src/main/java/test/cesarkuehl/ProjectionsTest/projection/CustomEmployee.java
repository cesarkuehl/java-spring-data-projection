package test.cesarkuehl.ProjectionsTest.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import test.cesarkuehl.ProjectionsTest.entity.Employee;

@Projection(
name = "customEmployee", 
types = { Employee.class }) 
public interface CustomEmployee {
	
	@Value("#{target.branch.company.name}")
	String getCompanyName();
	
	@Value("#{target.branch.company.description}")
	String getCompanyDescription();
	
	@Value("#{target.branch.name}")
	String getBranchName();
	
	@Value("#{target.branch.address.streetName}")
	String getAddressStreetName();
	
	@Value("#{target.branch.address.number}")
	String getAddressNumber();
	
	@Value("#{target.name}")
	String getName();
	
}
