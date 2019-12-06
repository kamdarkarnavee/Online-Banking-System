package edu.sjsu.cmpe202.banking_system.account_creation.checking_account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {
     CheckingAccount findByUserId (int user_id);

	//void withdraw(int from_account, double transaction_amount);
	//void deposit(int account_no, double transaction_amount);

}
