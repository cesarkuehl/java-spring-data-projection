package test.cesarkuehl.ProjectionsTest.querybuilder;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.querydsl.jpa.impl.JPAQuery;

import test.cesarkuehl.ProjectionsTest.entity.QAddress;
import test.cesarkuehl.ProjectionsTest.entity.QBranch;
import test.cesarkuehl.ProjectionsTest.entity.QCompany;
import test.cesarkuehl.ProjectionsTest.entity.QEmployee;

@Component
public class BranchQueryBuilder extends AbstractQueryBuilder {
	
	private QBranch branch = QBranch.branch;
	
	@Inject
	private AbstractQueryBuilder addressQueryBuilder;
	
	@Inject
	private AbstractQueryBuilder employeeQueryBuilder;
	
	@Inject
	private AbstractQueryBuilder companyQueryBuilder;

	@Override
	protected void evaluateFilterRelationship(JPAQuery jpaQuery, Predicate predicate, String relationshipName) {
		switch(relationshipName) {
			case "address": jpaQuery.innerJoin(branch.address, QAddress.address);
						    addressQueryBuilder.filter(jpaQuery, predicate);
						    break;
							
			case "employees": jpaQuery.innerJoin(branch.employeers, QEmployee.employee);
							  employeeQueryBuilder.filter(jpaQuery, predicate);
							  break;
							  
			case "company": jpaQuery.innerJoin(branch.company, QCompany.company);
							companyQueryBuilder.filter(jpaQuery, predicate);
							break;
							
			default: throw new InvalidFilterException(relationshipName + " is a invalid relationship name for entity branch");
		}
	}

	@Override
	protected void evaluateFilterAttribute(JPAQuery jpaQuery, Predicate predicate) {
		switch(predicate.getAttribute().toLowerCase()) {
			case "name": evaluatePredicate(jpaQuery, branch.name, predicate);
						 break;
						 
			default: throw new InvalidFilterException(predicate.getAttribute() + " is a invalid attribute for entity branch");
		}
	}
	
}
