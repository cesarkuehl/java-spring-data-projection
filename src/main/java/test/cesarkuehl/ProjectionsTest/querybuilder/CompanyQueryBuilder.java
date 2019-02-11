package test.cesarkuehl.ProjectionsTest.querybuilder;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.querydsl.jpa.impl.JPAQuery;

import test.cesarkuehl.ProjectionsTest.entity.QBranch;
import test.cesarkuehl.ProjectionsTest.entity.QCompany;

@Component
public class CompanyQueryBuilder extends AbstractQueryBuilder {
	
	private QCompany company = QCompany.company;
	
	@Inject
	private AbstractQueryBuilder branchQueryBuilder;
	
	@Override
	protected void evaluateFilterRelationship(JPAQuery jpaQuery, Predicate predicate, String relationshipName) {
		switch(relationshipName) {
			case "branches": jpaQuery.innerJoin(company.branches, QBranch.branch);
			   			     branchQueryBuilder.filter(jpaQuery, predicate);
			   			     break;
			   
			default: throw new InvalidFilterException(relationshipName + " is a invalid relationship name for entity company");
		}
	}

	@Override
	protected void evaluateFilterAttribute(JPAQuery jpaQuery, Predicate predicate) {
		switch(predicate.getAttribute().toLowerCase()) {
			case "name": evaluatePredicate(jpaQuery, company.name, predicate);
						 break;
			
			case "description": evaluatePredicate(jpaQuery, company.description, predicate);
			 			        break;			 
						 
			default: throw new InvalidFilterException(predicate.getAttribute() + " is a invalid attribute for entity company");
		}
		
	}

}
