package test.cesarkuehl.ProjectionsTest.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import test.cesarkuehl.ProjectionsTest.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address,Integer>{

}
