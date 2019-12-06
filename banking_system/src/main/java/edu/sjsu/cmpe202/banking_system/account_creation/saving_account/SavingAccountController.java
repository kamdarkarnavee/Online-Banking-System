package edu.sjsu.cmpe202.banking_system.account_creation.saving_account;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/account")
public class SavingAccountController {

    @Autowired
    private SavingAccountService savingAccountService;

    @GetMapping(value = "saving/allAccounts")
    public Collection<SavingAccount> getAllAccounts() {
        return savingAccountService.getAllAccounts();
    }


    @GetMapping(value = "saving/{account_no}")
    public Optional<SavingAccount> getAccountByNo(@PathVariable long account_no) {
        return savingAccountService.getAccountByNo(account_no);
    }


    @PostMapping(value = "/{user_id}/saving/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SavingAccount addAccount(@PathVariable(value = "user_id") int user_id, @RequestBody SavingAccount account) {
        return savingAccountService.addAccount(user_id, account);
    }

    /**Get User Account Balance**/
    @GetMapping(value = "/{user_id}/saving/balance")
    public double getAccountBalance(@PathVariable(value = "user_id") int user_id) {
        SavingAccount account = savingAccountService.getAccountByUserId(user_id);
        return account.getBalance();
    }


    @DeleteMapping(value = "/{user_id}/saving")
    public void deleteAccount(@PathVariable(value = "user_id") int user_id) {
        savingAccountService.deleteAccountById(user_id);
    }
}
