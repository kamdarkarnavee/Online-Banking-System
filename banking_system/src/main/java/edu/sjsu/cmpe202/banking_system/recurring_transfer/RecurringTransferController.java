package edu.sjsu.cmpe202.banking_system.recurring_transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/recurring_transfer")
public class RecurringTransferController 
{

	@Autowired
	RecurringTransferService recurringTransferService;
	
	@PostMapping("/{user_id}/{from_account}/{to_account}/add")
    public void addtransactions(@PathVariable(value = "user_id") int user_id,@PathVariable(value = "from_account") long from_account,
                                @PathVariable(value = "to_account") long to_account,@RequestBody RecurringTransfer recurringtransfer)
    {
		recurringTransferService.addRecurringTransfer(user_id,from_account,to_account,recurringtransfer);
    }

    @GetMapping("/pending_transactions")
    @Scheduled(fixedRate = 5000)
    public void findPendingTransactions(){
    	recurringTransferService.findPendingTransactions();
    }

    @DeleteMapping("/{user_id}/{from_account}/{to_account}/delete_RT")
    public void deleterecurringtransfer(@PathVariable(value = "user_id") int user_id,@PathVariable(value = "from_account") long from_account,
    		@PathVariable(value = "to_account") long to_account)
    {
    	recurringTransferService.deleteRT(user_id, from_account,to_account);
    }
}