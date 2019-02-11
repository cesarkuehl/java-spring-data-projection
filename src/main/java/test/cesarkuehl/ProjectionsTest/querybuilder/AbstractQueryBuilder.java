package test.cesarkuehl.ProjectionsTest.querybuilder;

import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;

public abstract class AbstractQueryBuilder {
	
	protected String getFirstArg(String value) {
		return value.substring(0,value.indexOf("."));
	}
	
	protected String getArgsRemovingFirst(String value) {
		String[] args = value.split("\\.");
		String response = "";
		
		for( int i = 1; i < args.length; i++ ) {
			if(i > 1)
				response += ".";
				
			response += args[i];
		}
		
		return response;
 	}
	
	protected JPAQuery evaluatePredicate(JPAQuery jpaQuery, StringPath stringPath, Predicate predicate) {
		switch(predicate.getComparator()) {
			case "eq": jpaQuery.where(stringPath.eq(predicate.getValue()));
					   break;
					   
			case "like": jpaQuery.where(stringPath.like(predicate.getValue()));
				 		 break;
				 		 
			default: throw new InvalidFilterException(predicate.getComparator() + " is invalid for literal attributes");	   
		}
		
		return jpaQuery;
	}
	
	public Predicate buildPredicate(String filter) {
		Predicate predicate = new Predicate();
		
		if(filter.toLowerCase().startsWith("containing")) {
			predicate.setComparator("like");
			predicate.setAttribute(filter.substring(filter.indexOf("containing('"),filter.indexOf("',")));
			predicate.setValue(filter.substring(filter.indexOf(",'"),filter.indexOf("')")));
		}
		else {
			String[] splittedFilter = filter.split(" ");
			
			predicate.setAttribute(splittedFilter[0]);
			predicate.setComparator(splittedFilter[1].toLowerCase());
			
			if( splittedFilter.length > 3 ) {
				String value = "";
				
				for(int i = 2; i < splittedFilter.length; i++) {
					if(i > 2)
						value += " ";
					
					value += splittedFilter[i];
				}
				
				predicate.setValue(value.substring(1,value.length()-1)); // Removes the apostrophes
			}
			else {
				predicate.setValue(splittedFilter[2].substring(1,splittedFilter[2].length()-1));
			}
		}
		
		return predicate;
	}
	
	public final JPAQuery filter(JPAQuery jpaQuery, Predicate predicate) {
		if(predicate.getAttribute().contains(".")) {
			String relationshiptName = getFirstArg(predicate.getAttribute());
			predicate.setAttribute(getArgsRemovingFirst(predicate.getAttribute()));
			
			evaluateFilterRelationship(jpaQuery, predicate, relationshiptName);
		}
		else {
			evaluateFilterAttribute(jpaQuery, predicate);
		}
		
		return jpaQuery;
	}
	
	protected abstract void evaluateFilterRelationship(JPAQuery jpaQuery, Predicate predicate, String relationshipName);
	
	protected abstract void evaluateFilterAttribute(JPAQuery jpaQuery, Predicate predicate);
	
}
