package edu.sjsu.cmpe202.banking_system.add_transactions;

import org.springframework.beans.factory.annotation.Autowired;

import edu.sjsu.cmpe202.banking_system.account_creation.saving_account.SavingAccountCustomInterface;
import edu.sjsu.cmpe202.banking_system.account_creation.saving_account.SavingAccountRepository;
import edu.sjsu.cmpe202.banking_system.transactions.Transactions;

public class AddTransactionsCustomInterfaceImplementation implements AddTransactionsCustomInterface {

	@Autowired
	SavingAccountRepository savingaccountrepository;
	
	@Autowired
	SavingAccountCustomInterface savingaccountcustominterface;
	
	public void performtransactions(Transactions transaction)
	{
		int from_account, to_account;
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
		//else
		//	checkingaccountrepository.withdraw(from_account, transaction_amount);
		
		flag = savingaccountrepository.existsById(to_account);
		if(flag == true)
		{
			//SavingAccount account_details = savingaccountrepository.findById(from_account).get();
			savingaccountcustominterface.deposit(to_account, transaction_amount);
			
		}
		//else
		//	checkingaccountrepository.deposit(to_account, transaction_amount);
		
		
	}


}
