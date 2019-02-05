package test.cesarkuehl.ProjectionsTest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import test.cesarkuehl.ProjectionsTest.entity.Branch;

@Repository
public interface BranchRepository extends CrudRepository<Branch,Integer> {

}
