package test.cesarkuehl.ProjectionsTest.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import test.cesarkuehl.ProjectionsTest.dto.EmployeeDTO;
import test.cesarkuehl.ProjectionsTest.dto.QBranchDTO;
import test.cesarkuehl.ProjectionsTest.dto.QEmployeeDTO;
import test.cesarkuehl.ProjectionsTest.entity.QEmployee;
import test.cesarkuehl.ProjectionsTest.querybuilder.AbstractQueryBuilder;
import test.cesarkuehl.ProjectionsTest.querybuilder.EmployeeQueryBuilder;

@RestController
public class EmployeeController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@RequestMapping(value = "/listEmployee", method = RequestMethod.GET)
	public List<EmployeeDTO> listEmployee(String filter, String sort) {
		QEmployee employee = QEmployee.employee;

		JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
		JPAQuery query = queryFactory.select(new QEmployeeDTO(employee.name, new QBranchDTO(employee.branch.name))).from(employee);
		
		if(filter != null && !filter.isEmpty()) {
			query = EmployeeQueryBuilder.filter(query, AbstractQueryBuilder.buildPredicate(filter));
		}
		
		return query.fetch();
	}
	
}
