package edu.sjsu.cmpe202.banking_system.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void addtransactions(Transactions transactions) {
        transactionRepository.save(transactions);
    }
}
