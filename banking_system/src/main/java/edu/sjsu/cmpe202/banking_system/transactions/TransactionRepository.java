package edu.sjsu.cmpe202.banking_system.transactions;

import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transactions,Integer>{
}
