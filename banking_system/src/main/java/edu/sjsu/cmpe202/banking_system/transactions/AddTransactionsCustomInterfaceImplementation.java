package edu.sjsu.cmpe202.banking_system.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe202.banking_system.account_creation.saving_account.SavingAccountCustomInterface;
import edu.sjsu.cmpe202.banking_system.account_creation.saving_account.SavingAccountRepository;
import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccountRepository;
import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccountCustomInterface;

import edu.sjsu.cmpe202.banking_system.transactions.Transactions;

@Service
public class AddTransactionsCustomInterfaceImplementation implements AddTransactionsCustomInterface {

	@Autowired
	SavingAccountRepository savingaccountrepository;
	
	@Autowired
	SavingAccountCustomInterface savingaccountcustominterface;
	
	@Autowired
	CheckingAccountRepository checkingaccountrepository;
	
	@Autowired
	CheckingAccountCustomInterface checkingaccountcustominterface;
	
	
	public void performtransactions(Transactions transaction)
	{
		long from_account, to_account;
		double transaction_amount;
		transaction_amount = transaction.getTransaction_amount();
		boolean flag;
		from_account = transaction.getFrom_account();
		to_account = transaction.getTo_account();
		//flag = savingaccountrepository.isSavingAccount(from_account);

		flag = savingaccountrepository.existsById(from_account);
		
		
		if(flag == true)
		{
			//SavingAccount account_details = savingaccountrepository.findById(from_account).get();
			savingaccountcustominterface.withdraw(from_account, transaction_amount);
			
		}
		else
			checkingaccountcustominterface.withdraw(from_account, transaction_amount);
		
		flag = savingaccountrepository.existsById(to_account);
		if(flag == true)
		{
			//SavingAccount account_details = savingaccountrepository.findById(from_account).get();
			savingaccountcustominterface.deposit(to_account, transaction_amount);
			
		}
		else
			checkingaccountcustominterface.deposit(to_account, transaction_amount);
		
		
	}


}
