package edu.sjsu.cmpe202.banking_system.account_creation.saving_account;

import org.springframework.beans.factory.annotation.Autowired;

public class SavingAccountCustomImplementation implements SavingAccountCustomInterface
{
	
	@Autowired
	SavingAccountRepository savingAccountRepository;
	
	public void withdraw(int account_no, double transaction_amount)
	{
		
		
		double balance;
		SavingAccount account_to_be_updated = savingAccountRepository.findById(account_no).get();
		balance = account_to_be_updated.getBalance() - transaction_amount;
		account_to_be_updated.setBalance(balance);
		savingAccountRepository.save(account_to_be_updated);
	}
	
	public void deposit(int account_no, double transaction_amount)
	{
		double balance;
		SavingAccount account_to_be_updated = savingAccountRepository.findById(account_no).get();
		balance = account_to_be_updated.getBalance() + transaction_amount;
		account_to_be_updated.setBalance(balance);
		savingAccountRepository.save(account_to_be_updated);
	}
}
