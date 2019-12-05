package edu.sjsu.cmpe202.banking_system.bill_payment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillPaymentRepository extends CrudRepository<BillPayment,Integer>{ @Query(value = "SELECT * from billpayment", nativeQuery = true)
public List<BillPayment> findByDate(@Param("date") String date) ;

}
