package edu.sjsu.cmpe202.banking_system.account_creation.checking_account;

public interface CheckingAccountCustomInterface 
{
	public void deposit(long account_no, double transaction_amount);
	public void withdraw(long account_no, double transaction_amount);

}
