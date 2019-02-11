package test.cesarkuehl.ProjectionsTest.querybuilder;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.querydsl.jpa.impl.JPAQuery;

import test.cesarkuehl.ProjectionsTest.entity.QAddress;
import test.cesarkuehl.ProjectionsTest.entity.QBranch;

@Component
public class AddressQueryBuilder extends AbstractQueryBuilder {
	
	private QAddress address = QAddress.address;
	
	@Inject
	private AbstractQueryBuilder branchQueryBuilder;

	@Override
	protected void evaluateFilterRelationship(JPAQuery jpaQuery, Predicate predicate, String relationshipName) {
		switch(relationshipName) {
			case "branch": jpaQuery.innerJoin(address.branch, QBranch.branch);
			   			   branchQueryBuilder.filter(jpaQuery, predicate);
			   			   break;
			   
			default: throw new InvalidFilterException(relationshipName + " is a invalid relationship name for entity address");
		}


	}

	@Override
	protected void evaluateFilterAttribute(JPAQuery jpaQuery, Predicate predicate) {
		switch(predicate.getAttribute().toLowerCase()) {
			case "streetName": evaluatePredicate(jpaQuery, address.streetName, predicate);
						 	   break;
			
			case "number": evaluatePredicate(jpaQuery, address.number, predicate);
		 	   			   break;			 	   
			
			default: throw new InvalidFilterException(predicate.getAttribute() + " is a invalid attribute for entity address");
		}
	}
	
	
	
}
