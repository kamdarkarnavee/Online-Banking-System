package edu.sjsu.cmpe202.banking_system.recurring_transfer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccountRepository;
import edu.sjsu.cmpe202.banking_system.account_creation.saving_account.SavingAccountRepository;
import edu.sjsu.cmpe202.banking_system.bill_payment.BillPayment;
import edu.sjsu.cmpe202.banking_system.payee.Payee;
import edu.sjsu.cmpe202.banking_system.transactions.AddTransactionsCustomInterfaceImplementation;
import edu.sjsu.cmpe202.banking_system.transactions.TransactionRepository;
import edu.sjsu.cmpe202.banking_system.transactions.Transactions;
import edu.sjsu.cmpe202.banking_system.user.User;
import edu.sjsu.cmpe202.banking_system.user.UserRepository;

@Service
public class RecurringTransferService 
{
    @Autowired
    private RecurringTransferRepository recurringTransferRepository;

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Autowired
    private SavingAccountRepository savingAccountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AddTransactionsCustomInterfaceImplementation addTransactionsCustomInterfaceImplementation;


    @Autowired
    UserRepository userRepository;

	public void addRecurringTransfer(int user_id, long from_account, long to_account, RecurringTransfer recurringtransfer) 
	{
		Optional<User> user = userRepository.findById(user_id);
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id " + user_id + " does not exist");
        }
        recurringtransfer.setUser(user.get());

        if (savingAccountRepository.existsById(from_account) || checkingAccountRepository.existsById(from_account)) 
        {
            
        	recurringtransfer.setTo_account(to_account);
        	recurringtransfer.setFrom_account(from_account);
        	
        	recurringtransfer.setStatus(RecurringTransfer.Status.PENDING);
        	RecurringTransfer new_rt = recurringTransferRepository.save(recurringtransfer);

            user.get().setRecurringTransfer(new_rt);
        }
	}

	public void findPendingTransactions() 
	{
        Date date = new Date();
        String query_date;
        List<Object[]> ob = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        query_date = formatter.format(date);
        var recurringtransfers = (List<RecurringTransfer>) recurringTransferRepository.findByDate(query_date);
        System.out.println("Query and date: RT " + recurringtransfers + query_date);
        addTransactions(recurringtransfers);
		
	}

	private void addTransactions(List<RecurringTransfer> recurringtransfers) 
	{
		for(int i = 0; i< recurringtransfers.size();i++)
        {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date today_date = new Date();
            Transactions transactions = new Transactions();
            transactions.setTransaction_date((today_date));
            transactions.setFrom_account(recurringtransfers.get(i).getFrom_account());
            transactions.setTo_account(recurringtransfers.get(i).getTo_account());
            transactions.setTransaction_amount(recurringtransfers.get(i).getTransaction_amount());
            transactions.setTransaction_details(recurringtransfers.get(i).getTransaction_details());
            transactions.setTransaction_description(recurringtransfers.get(i).getTransaction_description());


            addTransactionsCustomInterfaceImplementation.performtransactions(transactions);


            if(recurringtransfers.get(i).getPeriod() != null){
                if(recurringtransfers.get(i).getPeriod() == RecurringTransfer.Period.WEEKLY){
                    updateBillPaymentWeekly(recurringtransfers.get(i));
                }
                if(recurringtransfers.get(i).getPeriod() == RecurringTransfer.Period.MONTHLY){
                    updateBillPaymentsStatusMonthly(recurringtransfers.get(i));
                }

            }
            else
            {
                updateBillPaymentStatus(recurringtransfers.get(i));
            }
        }
	}

	private void updateBillPaymentStatus(RecurringTransfer recurringTransfer) 
	{
		recurringTransfer.setStatus(RecurringTransfer.Status.COMPLETED);
		recurringTransferRepository.save(recurringTransfer);		
	}

	private void updateBillPaymentsStatusMonthly(RecurringTransfer recurringTransfer) 
	{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try{
            cal.setTime(sdf.parse(recurringTransfer.getTransaction_date()));
        }catch(ParseException e){
            e.printStackTrace();
        }
        cal.add(Calendar.MONTH,1);
        if(cal.getTime().compareTo(recurringTransfer.getFinal_date()) < 0) {
        	recurringTransfer.setTransaction_date(sdf.format(cal.getTime()));
        	recurringTransferRepository.save(recurringTransfer);
        }
        else
        {
            updateBillPaymentStatus(recurringTransfer);
        }
		
	}

	private void updateBillPaymentWeekly(RecurringTransfer recurringTransfer) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try{
            cal.setTime(sdf.parse(recurringTransfer.getTransaction_date()));
        }catch(ParseException e){
            e.printStackTrace();
        }
        cal.add(Calendar.DATE,7);
        if(cal.getTime().compareTo(recurringTransfer.getFinal_date()) < 0) {
        	recurringTransfer.setTransaction_date(sdf.format(cal.getTime()));
        	recurringTransferRepository.save(recurringTransfer);
        }
        else
        {
            updateBillPaymentStatus(recurringTransfer);
        }
	}

	public void deleteRT(int user_id, long from_account, long to_account) 
	{
		
	}

}
