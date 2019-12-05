/*package edu.sjsu.cmpe202.banking_system.add_transactions;

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

    @PostMapping("/manual_transactions")
    public void addtransactions(@RequestBody Transactions transaction)
    {
    	addTransactionsAdminService.addtransactionsadmin(transaction);
    	//addTransactionsAdminService.performtransactions(transaction);

    }

}*/
