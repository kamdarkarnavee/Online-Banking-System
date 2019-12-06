package edu.sjsu.cmpe202.banking_system.account_creation.saving_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavingAccountCustomImplementation implements SavingAccountCustomInterface
{
	
	@Autowired
	SavingAccountRepository savingAccountRepository;
	
	public void withdraw(long account_no, double transaction_amount)
	{
		
		
		double balance;
		SavingAccount account_to_be_updated = savingAccountRepository.findById(account_no).get();
		balance = account_to_be_updated.getBalance() - transaction_amount;
		account_to_be_updated.setBalance(balance);
		savingAccountRepository.save(account_to_be_updated);
	}
	
	public void deposit(long account_no, double transaction_amount)
	{
		double balance;
		SavingAccount account_to_be_updated = savingAccountRepository.findById(account_no).get();
		balance = account_to_be_updated.getBalance() + transaction_amount;
		account_to_be_updated.setBalance(balance);
		savingAccountRepository.save(account_to_be_updated);
	}
	
	public double get_account_balance(long from_account)
	{
		return (savingAccountRepository.findById(from_account).get()).getBalance();
	}

}
