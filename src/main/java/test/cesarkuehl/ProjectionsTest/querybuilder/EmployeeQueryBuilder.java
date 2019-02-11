package test.cesarkuehl.ProjectionsTest.querybuilder;

import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;

import test.cesarkuehl.ProjectionsTest.entity.QBranch;
import test.cesarkuehl.ProjectionsTest.entity.QEmployee;

public class EmployeeQueryBuilder extends AbstractQueryBuilder {
	
	private static QEmployee employee = QEmployee.employee;
	
	public static JPAQuery filter(JPAQuery jpaQuery, Predicate predicate) {		
		if(predicate.getAttribute().contains(".")) {
			String arg = getFirstArg(predicate.getAttribute());
			
			if(arg.equals("branch")) {
				jpaQuery.innerJoin(employee.branch, QBranch.branch).fetchJoin();
				
				predicate.setAttribute(getArgsRemovingFirst(predicate.getAttribute()));
				
				return BranchQueryBuilder.filter(jpaQuery, predicate);
			}
			
			throw new InvalidFilterException(arg + " is invalid for entity employee");
		}
		else {
			switch(predicate.getAttribute().toLowerCase()) {
				case "name": evaluatePredicate(jpaQuery, employee.name, predicate);
							 break;
							 
				default: throw new InvalidFilterException(predicate.getAttribute() + " is invalid for entity employee");
			}
		}
		
		return jpaQuery;
	}
	
}
