package edu.sjsu.cmpe202.banking_system.bill_payment;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BillPaymentRepository extends CrudRepository<BillPayment,Integer>{ @Query(value = "SELECT * from billpayment " +
        " where transaction_date <= CONCAT(:date, '23:59:59') AND status = 0", nativeQuery = true)
public List<BillPayment> findByDate(@Param("date") String date) ;

    @Modifying
    @Query(value = "INSERT into transactions(transaction_date,from_account,to_account,transaction_amount,balance," +
            "transaction_details,transaction_description) VALUES (:transaction_date,:from_account,:to_account,:transaction_amount,:balance,:transaction_details," +
            ":transaction_description)",nativeQuery = true)
    @Transactional
    public int modifyingQueryInsertTransaction(@Param("transaction_date") String transaction_date, @Param("from_account")Long from_account,
                                               @Param("to_account")Long to_account, @Param("transaction_amount")double transaction_amount,
                                               @Param("balance")double balance, @Param("transaction_details")String transaction_details,
                                               @Param("transaction_description")String transaction_description);

//    @Modifying
//    @Query(value = "UPDATE billpayment SET transaction_date = :transaction_date     ":transaction_description)",nativeQuery = true)
//    @Transactional
//    public int updateBillPaymentWeekly(@Param("transaction_date") String transaction_date, @Param("from_account")Long from_account,
//                                               @Param("to_account")Long to_account, @Param("transaction_amount")double transaction_amount,
//                                               @Param("balance")double balance, @Param("transaction_details")String transaction_details,
//                                               @Param("transaction_description")String transaction_description);


}
