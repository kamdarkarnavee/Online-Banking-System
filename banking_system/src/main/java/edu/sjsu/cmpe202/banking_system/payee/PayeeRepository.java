package edu.sjsu.cmpe202.banking_system.payee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayeeRepository extends CrudRepository<Payee, Integer>{

}
