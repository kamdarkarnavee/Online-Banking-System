package edu.sjsu.cmpe202.banking_system.account_creation.checking_account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Integer> {
}
