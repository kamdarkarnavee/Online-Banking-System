package edu.sjsu.cmpe202.banking_system.account_creation.checking_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccount;
import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccountRepository;

@Service
public class CheckingAccountCustomImplementation implements CheckingAccountCustomInterface 
{
	@Autowired
	CheckingAccountRepository checkingAccountRepository;
	
	public void withdraw(long account_no, double transaction_amount)
	{
		double balance;
		CheckingAccount account_to_be_updated = checkingAccountRepository.findById(account_no).get();
		balance = account_to_be_updated.getBalance() - transaction_amount;
		account_to_be_updated.setBalance(balance);
		checkingAccountRepository.save(account_to_be_updated);
	}
	
	public void deposit(long account_no, double transaction_amount)
	{
		double balance;
		CheckingAccount account_to_be_updated = checkingAccountRepository.findById(account_no).get();
		balance = account_to_be_updated.getBalance() + transaction_amount;
		account_to_be_updated.setBalance(balance);
		checkingAccountRepository.save(account_to_be_updated);
	}
}
