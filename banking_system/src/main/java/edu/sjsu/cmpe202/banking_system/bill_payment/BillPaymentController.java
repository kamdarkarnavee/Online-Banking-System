package edu.sjsu.cmpe202.banking_system.bill_payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billpayment")
public class BillPaymentController {

    @Autowired
    private BillPaymentService billpaymentService;

    @PostMapping("/add")
    public void addtransactions(@RequestBody BillPayment billpayment)
    {
        billpaymentService.addBillPayment(billpayment);
    }

    @GetMapping("/pending_transactions")
    @Scheduled(fixedRate = 5000)
    public void findPendingTransactions(){
        billpaymentService.findPendingTransactions();
    }
}
