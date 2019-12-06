package edu.sjsu.cmpe202.banking_system.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


import edu.sjsu.cmpe202.banking_system.user.User;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/add")
    public void addtransactions(@RequestBody Transactions transactions)
    {
        transactionService.addtransactions(transactions);
    }
    
    /*@GetMapping("/all")
    public Iterable<User> getAllTransactions() {
        return TransactionService.getAllTransactions();
    }*/
    
    
}
