package edu.sjsu.cmpe202.banking_system.account_creation.checking_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public Optional<CheckingAccount> getAccountByNo(@PathVariable int account_no) {
        return checkingAccountService.getAccountByNo(account_no);
    }

    @PostMapping(value = "checking/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addAccount(@RequestBody CheckingAccount account) {
        return checkingAccountService.addAccount(account);
    }

    @PutMapping(value = "checking/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateAccount(@RequestBody CheckingAccount account) {
        return checkingAccountService.updateAccount(account);
    }

    @DeleteMapping(value = "checking/{account_no}")
    public String deleteAccount(@PathVariable int account_no) {
        return checkingAccountService.deleteAccountById(account_no);
    }
}
