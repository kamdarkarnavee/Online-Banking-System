package edu.sjsu.cmpe202.banking_system.bill_payment;

import edu.sjsu.cmpe202.banking_system.transactions.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SchedulePayment {

    @Autowired
    private BillPaymentRepository billPaymentRepository;
    SchedulePayment() {
        System.out.println("constructed");

    }


    public void check_pending_bills()
    {
        Date date = new Date();
        String query_date;
        List<Object[]> ob = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        query_date = formatter.format(date);
        var billPayments = (List<BillPayment>) billPaymentRepository.findByDate(query_date);
        //return billPayments;
        //ob = billPaymentRepository.findByDate(query_date);
        System.out.println("Query : "+ billPayments);
    }



}
