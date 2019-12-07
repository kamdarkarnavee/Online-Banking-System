package edu.sjsu.cmpe202.banking_system.view_transactions;

import edu.sjsu.cmpe202.banking_system.transactions.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ViewRepository extends CrudRepository<Transactions, Integer> {
    @Query(value = "select t from Transactions t where t.transaction_date between :start_date and :end_date")
    List<Transactions> findByTransaction_dateBetween(@Param("start_date") Date start_date, @Param("end_date") Date end_date);

}
