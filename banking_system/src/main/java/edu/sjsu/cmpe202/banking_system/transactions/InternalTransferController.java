package edu.sjsu.cmpe202.banking_system.transactions;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccountRepository;
import edu.sjsu.cmpe202.banking_system.account_creation.saving_account.SavingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Date;

@RestController
public class InternalTransferController {

    @Autowired
    SavingAccountRepository savingAccountRepository;

    @Autowired
    CheckingAccountRepository checkingAccountRepository;

    @Autowired
    AddTransactionsCustomInterfaceImplementation addTransaction;

    @PostMapping(value = "account/{user_id}/{transfer_type}/internalTransfer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void internalTransfer(@PathVariable int user_id, @PathVariable String transfer_type, @RequestBody Transactions transaction){
        long from_account = 0, to_account = 0;
        boolean flag = true;
        transfer_type = transfer_type.toLowerCase();

        try{
            if(transfer_type.equals("checking")){
                from_account = checkingAccountRepository.findByUserId(user_id).getChecking_account_no();
                to_account = savingAccountRepository.findByUserId(user_id).getSaving_account_no();
            }else if(transfer_type.equals("saving")){
                from_account = savingAccountRepository.findByUserId(user_id).getSaving_account_no();
                to_account = checkingAccountRepository.findByUserId(user_id).getChecking_account_no();
            }else{
                System.out.println("Invalid transfer type");
                flag = false;
            }
        }catch (NullPointerException e){
            System.out.println("Invalid transfer details");
            flag = false;
        }

        if(flag){
            transaction.setFrom_account(from_account);
            transaction.setTo_account(to_account);
            if(transaction.getTransaction_details() == null)
                transaction.setTransaction_details("debited");
            transaction.setTransaction_date(new Date());
            addTransaction.performtransactions(transaction);
        }

    }


}
