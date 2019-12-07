package edu.sjsu.cmpe202.banking_system.view_transactions;

import edu.sjsu.cmpe202.banking_system.transactions.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("view_transactions")
public class ViewTransactionController {

    @Autowired
    ViewService viewService;


    @GetMapping(value = "{account_no}")
    public List<Transactions> viewTransactions(@PathVariable long account_no,
                                               @RequestParam(value = "transaction_type", required = false) String transaction_type,
                                               @RequestParam(value = "start_date", required = false) String start_date,
                                               @RequestParam(value = "end_date", required = false) String end_date) throws ParseException {

        return viewService.viewTransactions(account_no, transaction_type, start_date, end_date);

    }
}
