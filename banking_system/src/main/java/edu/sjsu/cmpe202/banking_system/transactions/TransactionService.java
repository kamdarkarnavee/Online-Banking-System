package edu.sjsu.cmpe202.banking_system.transactions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe202.banking_system.user.User;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public void addtransactions(Transactions transactions) {
        transactionRepository.save(transactions);
    }

	/*public static Iterable<User> getAllTransactions() {
		List<User> transactions = new ArrayList<>();
		transactionRepository.findAll().forEach(transactions::add);
        return transactions;
	}*/
}
