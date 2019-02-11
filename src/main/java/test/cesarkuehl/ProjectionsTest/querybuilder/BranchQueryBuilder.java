package test.cesarkuehl.ProjectionsTest.querybuilder;

import com.querydsl.jpa.impl.JPAQuery;

import test.cesarkuehl.ProjectionsTest.entity.QBranch;

public class BranchQueryBuilder extends AbstractQueryBuilder {
	
	private static QBranch branch = QBranch.branch;
	
	public static JPAQuery filter(JPAQuery jpaQuery, Predicate predicate) {		
		if(predicate.getAttribute().contains(".")) {
			String arg = getFirstArg(predicate.getAttribute());
			
			throw new InvalidFilterException(arg + " is invalid for entity employee");
		}
		else {
			switch(predicate.getAttribute().toLowerCase()) {
				case "name": evaluatePredicate(jpaQuery, branch.name, predicate);
							 break;
							 
				default: throw new InvalidFilterException(predicate.getAttribute() + " is invalid for entity branch");
			}
		}
		
		return jpaQuery;
	}
	
}
