package edu.sjsu.cmpe202.banking_system.account_creation.checking_account;

import edu.sjsu.cmpe202.banking_system.account_creation.saving_account.SavingAccount;
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

        //tie User to CheckingAccount
        account.setUser(user.get());
        CheckingAccount newAccount = checkingAccountRepository.save(account);

        //tie CheckingAccount to User
        user.get().setCheckingAccount(newAccount);

        return newAccount;

    }

    /**
     * Deactivate existing account
     */
    public String deleteAccountById(long account_no) {
        CheckingAccount ca = checkingAccountRepository.findById(account_no).orElse(null);
        if (ca != null) {
//            accountRepository.deleteById(account_no);
            return "Account deleted";
        } else
            return "Account deletion failed";

    }
    
    
    /*public void withdraw(int account_no, double transaction_amount)
	{
		double balance;
		CheckingAccount account_to_be_updated = checkingAccountRepository.findById(account_no).get();
		balance = account_to_be_updated.getBalance() - transaction_amount;
		account_to_be_updated.setBalance(balance);
		checkingAccountRepository.save(account_to_be_updated);
	}
	
	public void deposit(int account_no, double transaction_amount)
	{
		double balance;
		CheckingAccount account_to_be_updated = checkingAccountRepository.findById(account_no).get();
		balance = account_to_be_updated.getBalance() + transaction_amount;
		account_to_be_updated.setBalance(balance);
		checkingAccountRepository.save(account_to_be_updated);
	}*/
}
