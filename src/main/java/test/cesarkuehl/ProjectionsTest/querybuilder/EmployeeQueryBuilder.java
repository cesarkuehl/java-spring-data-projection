package test.cesarkuehl.ProjectionsTest.querybuilder;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.querydsl.jpa.impl.JPAQuery;

import test.cesarkuehl.ProjectionsTest.entity.QBranch;
import test.cesarkuehl.ProjectionsTest.entity.QEmployee;

@Component
public class EmployeeQueryBuilder extends AbstractQueryBuilder {
	
	@Inject
	private AbstractQueryBuilder branchQueryBuilder;
	
	private QEmployee employee = QEmployee.employee;

	@Override
	protected void evaluateFilterRelationship(JPAQuery jpaQuery, Predicate predicate, String relationshipName) {
		switch(relationshipName) {
			case "branch": jpaQuery.innerJoin(employee.branch, QBranch.branch);
						   branchQueryBuilder.filter(jpaQuery, predicate);
						   break;
						   
			default: throw new InvalidFilterException(relationshipName + " is a invalid relationship name for entity employee");
		}
	}

	@Override
	protected void evaluateFilterAttribute(JPAQuery jpaQuery, Predicate predicate) {
		switch(predicate.getAttribute()) {
			case "name": evaluatePredicate(jpaQuery, employee.name, predicate);
						 break;
						 
			default: throw new InvalidFilterException(predicate.getAttribute() + " is invalid attribute for entity employee");
		}
	}
	
}
