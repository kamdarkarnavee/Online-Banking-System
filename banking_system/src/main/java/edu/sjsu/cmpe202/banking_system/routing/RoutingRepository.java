package edu.sjsu.cmpe202.banking_system.routing;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutingRepository extends CrudRepository <Routing,Integer> {
    Routing findByBankName(String bankName);
}
