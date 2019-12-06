package edu.sjsu.cmpe202.banking_system.account_creation.checking_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/account")
public class CheckingAccountController {

    @Autowired
    private CheckingAccountService checkingAccountService;

    @GetMapping(value = "checking/allAccounts")
    public Collection<CheckingAccount> getAllAccounts() {
        return checkingAccountService.getAllAccounts();
    }


    @GetMapping(value = "checking/{account_no}")
    public Optional<CheckingAccount> getAccountByNo(@PathVariable long account_no) {
        return checkingAccountService.getAccountByNo(account_no);
    }


    @PostMapping(value = "/{user_id}/checking/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CheckingAccount addAccount(@PathVariable(value = "user_id") int user_id, @RequestBody CheckingAccount account) {
        return checkingAccountService.addAccount(user_id, account);
    }

    /**Get User Account Balance**/
    @GetMapping(value = "/{user_id}/checking/balance")
    public double getAccountBalance(@PathVariable(value = "user_id") int user_id) {
        CheckingAccount account = checkingAccountService.getAccountByUserId(user_id);
        return account.getBalance();
    }


    @DeleteMapping(value = "checking/{account_no}")
    public String deleteAccount(@PathVariable long account_no) {
        return checkingAccountService.deleteAccountById(account_no);
    }
}
