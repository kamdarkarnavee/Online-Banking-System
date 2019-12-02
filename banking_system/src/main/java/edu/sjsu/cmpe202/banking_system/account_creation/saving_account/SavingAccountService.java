package edu.sjsu.cmpe202.banking_system.account_creation.saving_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class SavingAccountService {

    @Autowired
    private SavingAccountRepository savingAccountRepository;

    /**
     * Displays only the active accounts
     */
    public Collection<SavingAccount> getAllAccounts() {
        Collection<SavingAccount> accounts = new ArrayList<>();
        for (SavingAccount account : savingAccountRepository.findAll()) {
            if (account.getAccount_status())
                accounts.add(account);
        }

//        Displays all the activated and deactivated accounts
//        accountRepository.findAll().forEach(accounts::add);

        return accounts;
    }

    /**
     * Find Account by account number
     */
    public Optional<SavingAccount> getAccountByNo(int account_no) {
        return savingAccountRepository.findById(account_no);
    }

    /**
     * Add a new account
     */
    public String addAccount(SavingAccount account) {
        SavingAccount ca = savingAccountRepository.findById(account.getSaving_account_no()).orElse(null);
        if (ca != null && ca.getUser_id() == account.getUser_id())
            ca = null;

        try {
            if (ca == null) {
                savingAccountRepository.save(account);
                return "Account created successfully";
            } else
                return "Account creation failed";
        } catch (Exception e) {
            return "Account creation failed";
        }
    }

    /**
     * Deactivate existing account
     */
    public String deleteAccountById(int account_no) {
        SavingAccount ca = savingAccountRepository.findById(account_no).orElse(null);
        if (ca != null) {
            ca.setAccount_status(false);
            savingAccountRepository.save(ca);
            return "Account Deleted";
        } else
            return "Account deletion failed";
//            accountRepository.deleteById(account_no);
    }

    /**
     * Update balance and account status of the existing account (doesn't update account number, user id or account creation date)
     */
    public String updateAccount(SavingAccount account) {
        SavingAccount ca = savingAccountRepository.findById(account.getSaving_account_no()).orElse(null);
        if (ca != null) {
            ca.setBalance(account.getBalance());
            ca.setAccount_status(account.getAccount_status());
            savingAccountRepository.save(ca);
            return "Account updated";
        } else
            return "Account update failed";

    }
}
