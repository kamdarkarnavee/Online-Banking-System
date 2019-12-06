package edu.sjsu.cmpe202.banking_system.transactions;

import org.springframework.stereotype.Service;

import edu.sjsu.cmpe202.banking_system.transactions.Transactions;

@Service
public interface AddTransactionsCustomInterface 
{
		//public void deposit(int account_no, double transaction_amount);
		//public void withdraw(int account_no, double transaction_amount);
		public void performtransactions(Transactions transaction);


}
