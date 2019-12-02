package edu.sjsu.cmpe202.banking_system.account_creation.saving_account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingAccountRepository extends JpaRepository<SavingAccount, Integer> {
}
