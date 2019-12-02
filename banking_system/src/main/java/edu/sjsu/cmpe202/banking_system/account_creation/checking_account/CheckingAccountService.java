package edu.sjsu.cmpe202.banking_system.account_creation.checking_account;

import edu.sjsu.cmpe202.banking_system.user.User;
import edu.sjsu.cmpe202.banking_system.user.UserRepository;
import edu.sjsu.cmpe202.banking_system.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CheckingAccountService {

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Displays only the active accounts
     */
    public Collection<CheckingAccount> getAllAccounts() {
        Collection<CheckingAccount> accounts = new ArrayList<>();
        for (CheckingAccount account : checkingAccountRepository.findAll()) {
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
    public Optional<CheckingAccount> getAccountByNo(int account_no) {
        return checkingAccountRepository.findById(account_no);
    }

    /**
     * Add a new account
     */
    public CheckingAccount addAccount(int user_id, CheckingAccount account) {
        Optional<User> user = userRepository.findById(user_id);
        if (!user.isPresent()) {
            throw  new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not exist");
        }

        //tie User to CheckingAccount
        account.setUser(user.get());
        CheckingAccount newAccount = checkingAccountRepository.save(account);

        //tie CheckingAccount to User
        user.get().setCheckingAccount(newAccount);

        return newAccount;

    }
    /*
    public String addAccount(CheckingAccount account) {
        CheckingAccount ca = checkingAccountRepository.findById(account.getChecking_account_no()).orElse(null);
        if (ca != null && ca.getUser().getId() == account.getUser().getId())
            ca = null;

        try {
            if (ca == null) {
                checkingAccountRepository.save(account);
                return "Account created successfully";
            } else
                return "Account creation failed";
        } catch (Exception e) {
            return "Account creation failed";
        }
    }*/

    /**
     * Deactivate existing account
     */
    public String deleteAccountById(int account_no) {
        CheckingAccount ca = checkingAccountRepository.findById(account_no).orElse(null);
        if (ca != null) {
            ca.setAccount_status(false);
            checkingAccountRepository.save(ca);
            return "Account deleted";
        } else
            return "Account deletion failed";
//            accountRepository.deleteById(account_no);
    }

    /**
     * Update balance and account status of the existing account (doesn't update account number, user id or account creation date)
     */
    public String updateAccount(CheckingAccount account) {
        CheckingAccount ca = checkingAccountRepository.findById(account.getChecking_account_no()).orElse(null);
        if (ca != null) {
            ca.setBalance(account.getBalance());
            ca.setAccount_status(account.getAccount_status());
            checkingAccountRepository.save(ca);
            return "Account updated";
        } else
            return "Account update failed";

    }
}
