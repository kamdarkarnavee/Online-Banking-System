package edu.sjsu.cmpe202.banking_system.transactions;

import org.springframework.beans.factory.annotation.Autowired;

import edu.sjsu.cmpe202.banking_system.transactions.TransactionRepository;
//import edu.sjsu.cmpe202.banking_system.transactions.Transactions;


import org.springframework.stereotype.Service;


@Service
public class AddTransactionsAdminService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	

	public void addtransactionsadmin(Transactions transaction) {

		transactionRepository.save(transaction);
        //addTransactionsCustomInterface.performtransactions(transaction);


	}
	
	


}
