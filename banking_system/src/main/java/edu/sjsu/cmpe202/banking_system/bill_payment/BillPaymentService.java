package edu.sjsu.cmpe202.banking_system.bill_payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BillPaymentService {

    @Autowired
    private BillPaymentRepository billPaymentRepository;


    public void addBillPayment(BillPayment billpayment) {
        billPaymentRepository.save(billpayment);
    }

    public void findPendingTransactions() {
        Date date = new Date();
        String query_date;
        List<Object[]> ob = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        query_date = formatter.format(date);
        var billPayments = (List<BillPayment>) billPaymentRepository.findByDate(query_date);
        System.out.println("Query and date: "+ billPayments+query_date);
    }
}
