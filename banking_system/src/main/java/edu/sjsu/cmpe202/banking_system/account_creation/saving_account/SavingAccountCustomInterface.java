package edu.sjsu.cmpe202.banking_system.account_creation.saving_account;

import org.springframework.stereotype.Service;

@Service
public interface SavingAccountCustomInterface 
{
	public void deposit(long account_no, double transaction_amount);
	public void withdraw(long account_no, double transaction_amount);
	public double get_account_balance(long from_account);

	
}
