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
	
	@Autowired
	TransactionRepository transactionRepository;
	
	
	double balance;

	/*
	 * Perform transactions 
	 * using account details
	 */
	public void performtransactions(Transactions transaction)
	{
		long from_account, to_account;
		double transaction_amount;
		String account_type;
		transaction_amount = transaction.getTransaction_amount();
		boolean flag;
		from_account = transaction.getFrom_account();
		to_account = transaction.getTo_account();
		

		flag = savingaccountrepository.existsById(from_account);
		
		
		if(flag == true)
		{
			account_type = "SAVING";
			balance = get_transaction_balance(account_type, from_account);
			update_transaction_balance(balance, transaction);
			
			savingaccountcustominterface.withdraw(from_account, transaction_amount);
			
		}
		else if(checkingaccountrepository.existsById(from_account))
		{
			account_type = "CHECKING";
			balance = get_transaction_balance(account_type, from_account);	
			update_transaction_balance(balance, transaction);

			checkingaccountcustominterface.withdraw(from_account, transaction_amount);
		}
		else
		{
			account_type = "NOT_DEFINED";
			
			System.out.println("from_account does not exist in our database, please try again");
		}
		
		
		flag = savingaccountrepository.existsById(to_account);
		if(flag == true)
		{
			savingaccountcustominterface.deposit(to_account, transaction_amount);
			
		}
		else if(checkingaccountrepository.existsById(to_account))
		{
			checkingaccountcustominterface.deposit(to_account, transaction_amount);
		}
		else
			System.out.println("to_account does not exist in our database, please try again");
		
	}
	/*
	 * GET from_account's 
	 * available balance before transaction
	 */
	private double get_transaction_balance(String account_type, long from_account) 
	{
		switch(account_type)
		{
		case "SAVING":
			balance = savingaccountcustominterface.get_account_balance(from_account);
			break;
		case "CHECKING":
			balance = checkingaccountcustominterface.get_account_balance(from_account);
			break;
		case "NOT_DEFINED":
            System.out.println("Invalid account number");
			balance = 0.0;
			break;
		default:
            System.out.println("Invalid account number");
            balance = 0.0;
            
		}
		return balance;
	}
	/*
	 * UPDATE BALANCE OF from_account in
	 *  transaction table
	 */
	public void update_transaction_balance(double balance, Transactions transaction)
	{
		transaction.setBalance(balance);
		transactionRepository.save(transaction);

	}
	
	/*
	 * Perform Manual Refunds by admin
	 */
	/*public void manual_refunds_admin(Transactions transaction)
	{
		
		long from_account, to_account;
		double transaction_amount;
		transaction_amount = transaction.getTransaction_amount();
		boolean flag;
		from_account = transaction.getFrom_account();
		to_account = transaction.getTo_account();

		flag = savingaccountrepository.existsById(from_account);
		
		
		if(flag == true)
		{
			savingaccountcustominterface.withdraw(from_account, transaction_amount);
			
		}
		else if(checkingaccountrepository.existsById(from_account))
		{
				checkingaccountcustominterface.withdraw(from_account, transaction_amount);
		}
		else
			System.out.println("from_account does not exist in our database, please try again");
		
		
		flag = savingaccountrepository.existsById(to_account);
		if(flag == true)
		{
			savingaccountcustominterface.deposit(to_account, transaction_amount);
			
		}
		else if(checkingaccountrepository.existsById(to_account))
		{
			checkingaccountcustominterface.deposit(to_account, transaction_amount);
		}
		else
			System.out.println("to_account does not exist in our database, please try again");
		
	} */
	
	
	


}
