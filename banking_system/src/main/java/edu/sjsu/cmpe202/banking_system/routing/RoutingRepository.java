package edu.sjsu.cmpe202.banking_system.routing;

import org.springframework.data.repository.CrudRepository;

public interface RoutingRepository extends CrudRepository <Routing,Integer> {

    Routing findByBank_name(String bank_name);
}
