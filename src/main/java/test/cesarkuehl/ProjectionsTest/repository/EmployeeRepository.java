package test.cesarkuehl.ProjectionsTest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import test.cesarkuehl.ProjectionsTest.entity.Employee;
import test.cesarkuehl.ProjectionsTest.projection.CustomEmployee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
	
	Page<CustomEmployee> findAllProjectedBy(Pageable pageable);
	
}
