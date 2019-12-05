package edu.sjsu.cmpe202.banking_system.add_transactions;

/*import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccountRepository;
import edu.sjsu.cmpe202.banking_system.account_creation.saving_account.SavingAccountRepository;
import edu.sjsu.cmpe202.banking_system.transactions.TransactionRepository;
import edu.sjsu.cmpe202.banking_system.transactions.Transactions;

public class AddTransactionsAdminService {
	

	private TransactionRepository transactionRepository;
	private SavingAccountRepository savingaccountrepository;
	private CheckingAccountRepository checkingaccountrepository;


	public void addtransactionsadmin(Transactions transaction) {

		transactionRepository.save(transaction);
        AddTransactionsCustomInterface.performtransactions(transaction);


	}
	
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
			savingaccountrepository.withdraw(from_account, transaction_amount);
			
		}
		else
			checkingaccountrepository.withdraw(from_account, transaction_amount);
		
		flag = savingaccountrepository.existsById(to_account);
		if(flag == true)
		{
			//SavingAccount account_details = savingaccountrepository.findById(from_account).get();
			savingaccountrepository.deposit(to_account, transaction_amount);
			
		}
		else
			checkingaccountrepository.deposit(to_account, transaction_amount);
		
		
	}
	


}
*/