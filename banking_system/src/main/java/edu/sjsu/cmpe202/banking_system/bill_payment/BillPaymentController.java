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

    @PostMapping("/{user_id}/{from_account}/{payee_id}/add")
    public void addtransactions(@PathVariable(value = "user_id") int user_id,@PathVariable(value = "from_account") long from_account,
                                @PathVariable(value = "payee_id") int payee_id,@RequestBody BillPayment billpayment)
    {
        billpaymentService.addBillPayment(user_id,from_account,payee_id,billpayment);
    }

    @GetMapping("/pending_transactions")
    @Scheduled(fixedRate = 5000)
    public void findPendingTransactions(){
        billpaymentService.findPendingTransactions();
    }
}
