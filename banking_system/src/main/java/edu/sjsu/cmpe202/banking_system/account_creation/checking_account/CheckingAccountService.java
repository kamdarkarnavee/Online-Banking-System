package edu.sjsu.cmpe202.banking_system.account_creation.checking_account;

import edu.sjsu.cmpe202.banking_system.user.User;
import edu.sjsu.cmpe202.banking_system.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

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
            accounts.add(account);
        }

//        Displays all the activated and deactivated accounts
//        accountRepository.findAll().forEach(accounts::add);

        return accounts;
    }

    /**
     * Find Account by account number
     */
    public Optional<CheckingAccount> getAccountByNo(long account_no) {
        return checkingAccountRepository.findById(account_no);
    }

    /**
     * Find Account by user id
     */
    public CheckingAccount getAccountByUserId(int user_id) {
        return checkingAccountRepository.findByUserId(user_id);
    }

    /**
     * Add a new account
     */
    public CheckingAccount addAccount(int user_id, CheckingAccount account) {
        Optional<User> user = userRepository.findById(user_id);
        if (!user.isPresent()) {
            throw  new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not exist");
        }

        try {
            if (checkingAccountRepository.findById(account.getChecking_account_no()).isEmpty()) {
                account.setUser(user.get());
                account.setAccount_creation_date(new Date());
                CheckingAccount newAccount = checkingAccountRepository.save(account);

                //tie CheckingAccount to User
                user.get().setCheckingAccount(newAccount);

                return newAccount;

            } else {
                throw new ResponseStatusException(NOT_ACCEPTABLE, "Entered Existing User Id or Account number");
            }

        } catch (Exception e) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "Entered Existing User Id or Account number");
        }


    }

    /**
     * Deactivate existing account
     */
    public void deleteAccountById(int user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (!user.isPresent()) {
            throw  new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not exist");
        }
        if(user.get().getCheckingAccount()!= null) {
            //untie User to CheckingAccount
            user.get().getCheckingAccount().setUser(null);

            //untie CheckingAccount to User
            user.get().setCheckingAccount(null);
            userRepository.save(user.get());
            throw  new ResponseStatusException(OK, "Successfully deleted checking account");
        }
        else
            throw  new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not have a checking account");
    }

}
