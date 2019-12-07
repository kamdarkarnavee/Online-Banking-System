package edu.sjsu.cmpe202.banking_system.recurring_transfer;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurringTransferRepository extends CrudRepository<RecurringTransfer,Integer>
{ 
	@Query(value = "SELECT * from recurringtransfer " +
        " where transaction_date <= CONCAT(:date, '23:59:59') AND status = 0", nativeQuery = true)
	
	public List<RecurringTransfer> findByDate(@Param("date") String date) ;


}