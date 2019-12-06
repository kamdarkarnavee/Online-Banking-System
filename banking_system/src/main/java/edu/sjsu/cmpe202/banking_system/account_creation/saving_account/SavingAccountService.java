package edu.sjsu.cmpe202.banking_system.account_creation.saving_account;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccount;
import edu.sjsu.cmpe202.banking_system.user.User;
import edu.sjsu.cmpe202.banking_system.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
public class SavingAccountService {

    @Autowired
    private SavingAccountRepository savingAccountRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Displays only the active accounts
     */
    public Collection<SavingAccount> getAllAccounts() {
        Collection<SavingAccount> accounts = new ArrayList<>();
        for (SavingAccount account : savingAccountRepository.findAll()) {
            accounts.add(account);
        }

//        Displays all the activated and deactivated accounts
//        accountRepository.findAll().forEach(accounts::add);

        return accounts;
    }

    /**
     * Find Account by account number
     */
    public Optional<SavingAccount> getAccountByNo(long account_no) {
        return savingAccountRepository.findById(account_no);
    }

    /**
     * Find Account by user id
     */
    public SavingAccount getAccountByUserId(int user_id) {
        return savingAccountRepository.findByUserId(user_id);
    }

    /**
     * Add a new account
     */
    public SavingAccount addAccount(int user_id, SavingAccount account) {
        Optional<User> user = userRepository.findById(user_id);
        if (!user.isPresent()) {
            throw  new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not exist");
        }

        //tie User to CheckingAccount
        account.setUser(user.get());
        SavingAccount newAccount = savingAccountRepository.save(account);

        //tie CheckingAccount to User
        user.get().setSavingAccount(newAccount);

        return newAccount;

    }


    /**
     * Deactivate existing account
     */
    public void deleteAccountById(int user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (!user.isPresent()) {
            throw  new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not exist");
        }
        if(user.get().getSavingAccount()!= null) {
            //untie User to CheckingAccount
            user.get().getSavingAccount().setUser(null);

            //untie CheckingAccount to User
            user.get().setSavingAccount(null);
            userRepository.save(user.get());
            throw  new ResponseStatusException(OK, "Successfully deleted saving account");
        }
        else
            throw  new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not have a saving account");
    }

    }

}
