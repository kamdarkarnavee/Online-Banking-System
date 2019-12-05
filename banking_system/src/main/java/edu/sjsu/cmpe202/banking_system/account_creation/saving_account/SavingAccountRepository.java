package edu.sjsu.cmpe202.banking_system.account_creation.saving_account;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, Long> {
     SavingAccount findByUserId (int user_id);

	//boolean isSavingAccount(int from_account);

	//SavingAccount withdraw(int account_no, double transaction_amount);
	//SavingAccount deposit(int account_no, double transaction_amount);

}
