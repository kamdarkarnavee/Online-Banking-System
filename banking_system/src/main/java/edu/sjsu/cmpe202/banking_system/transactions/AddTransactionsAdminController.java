package edu.sjsu.cmpe202.banking_system.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import edu.sjsu.cmpe202.banking_system.transactions.Transactions;


@RestController
@RequestMapping("/admin/transactions")
public class AddTransactionsAdminController {

    @Autowired
	private AddTransactionsAdminService addTransactionsAdminService;
	
	@Autowired
	private AddTransactionsCustomInterface addtransactionscustominterface;

    @PostMapping("/manual_transactions")
    public void addtransactions(@RequestBody Transactions transaction)
    {
    	addTransactionsAdminService.addtransactionsadmin(transaction); // to insert a transaction record in database
    	
    	addtransactionscustominterface.performtransactions(transaction); // to perform deposit or withdraw
    	

    }
    
    @PostMapping("/manual_refunds")
    public void manualrefunds(@RequestBody Transactions transaction)
    {
    	if(transaction.getFrom_account() == 1111111111)
    	{
    		addTransactionsAdminService.addtransactionsadmin(transaction); // to insert a transaction record in database
    	
    		addtransactionscustominterface.manual_refunds_admin(transaction); // to credit refund for a user
    	}
    	

    }
    
    @PostMapping("/manual_credit")
    public void manualcredit(@RequestBody Transactions transaction)
    {
    	if(transaction.getFrom_account() == 1111111112)
    	{
    		addTransactionsAdminService.addtransactionsadmin(transaction); // to insert a transaction record in database
    	
    		addtransactionscustominterface.manual_credits_admin(transaction); // to deposit money in user's account
    	}
    	

    }
    

}