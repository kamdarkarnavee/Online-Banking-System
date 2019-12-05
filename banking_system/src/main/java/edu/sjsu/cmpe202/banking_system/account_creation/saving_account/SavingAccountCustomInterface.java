package edu.sjsu.cmpe202.banking_system.account_creation.saving_account;

public interface SavingAccountCustomInterface 
{
	public void deposit(int account_no, double transaction_amount);
	public void withdraw(int account_no, double transaction_amount);

	
}
