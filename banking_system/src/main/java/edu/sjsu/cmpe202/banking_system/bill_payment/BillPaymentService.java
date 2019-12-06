package edu.sjsu.cmpe202.banking_system.bill_payment;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccountRepository;
import edu.sjsu.cmpe202.banking_system.account_creation.saving_account.SavingAccountRepository;
import edu.sjsu.cmpe202.banking_system.payee.Payee;
import edu.sjsu.cmpe202.banking_system.payee.PayeeRepository;
import edu.sjsu.cmpe202.banking_system.transactions.AddTransactionsCustomInterfaceImplementation;
import edu.sjsu.cmpe202.banking_system.transactions.TransactionRepository;
import edu.sjsu.cmpe202.banking_system.transactions.Transactions;
import edu.sjsu.cmpe202.banking_system.user.User;
import edu.sjsu.cmpe202.banking_system.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Null;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BillPaymentService {

    @Autowired
    private BillPaymentRepository billPaymentRepository;

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Autowired
    private SavingAccountRepository savingAccountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AddTransactionsCustomInterfaceImplementation addTransactionsCustomInterfaceImplementation;

    @Autowired
    PayeeRepository payeeRepository;

    @Autowired
    UserRepository userRepository;


    public void addBillPayment(int user_id,long from_account,int payee_id,BillPayment billpayment) {
        Optional<User> user = userRepository.findById(user_id);
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author with id " + user_id + " does not exist");
        }
            billpayment.setUser_id(user.get());

            if (savingAccountRepository.existsById(from_account) || checkingAccountRepository.existsById(from_account)) {
                Optional<Payee> payee = payeeRepository.findById(payee_id);
                if (!payee.isPresent())
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Payee with id " + user_id + " does not exist");
                billpayment.setPayee(payee.get());
                System.out.println("AAAAAAAAAAAAAAAAAAAAAA"+from_account + payee.get().getAccount_number_payee());
                billpayment.setFrom_account(from_account);
                billpayment.setTo_account(billpayment.getPayee().getAccount_number_payee());
                //billpayment.setfromAndtoAccount(billpayment.getTransactions(),from_account, (long) billpayment.getPayee().getAccount_number_payee());
                billpayment.setStatus(BillPayment.Status.PENDING);
                BillPayment newbillpayment = billPaymentRepository.save(billpayment);

                user.get().setBillpayment(newbillpayment);
            }
        }



    public void findPendingTransactions() {
        Date date = new Date();
        String query_date;
        List<Object[]> ob = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        query_date = formatter.format(date);
        var billPayments = (List<BillPayment>) billPaymentRepository.findByDate(query_date);
        System.out.println("Query and date: " + billPayments + query_date);
        addTransactions(billPayments);
    }

    public void addTransactions(List<BillPayment> billPayments){

        for(int i = 0; i< billPayments.size();i++)
        {
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date today_date = new Date();
            billPaymentRepository.modifyingQueryInsertTransaction((formater.format(today_date)),billPayments.get(i).getFrom_account(),
                    billPayments.get(i).getTo_account(),billPayments.get(i).getTransaction_amount(),billPayments.get(i).getBalance(),
                    billPayments.get(i).getTransaction_details(),billPayments.get(i).getTransaction_description());

            if(billPayments.get(i).getPeriod() != null){
                if(billPayments.get(i).getPeriod() == BillPayment.Period.WEEKLY){
                    updateBillPaymentWeekly(billPayments.get(i));
                }

            }
            else
            {
                updateBillPaymentStatus(billPayments.get(i));
            }
        }
    }

    public void updateBillPaymentWeekly(BillPayment billPayment){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try{
        cal.setTime(sdf.parse(billPayment.getTransaction_date()));
        }catch(ParseException e){
            e.printStackTrace();
        }
        cal.add(Calendar.DATE,7);
        if(cal.getTime().compareTo(billPayment.getFinal_date()) < 0) {
            billPayment.setTransaction_date(sdf.format(cal.getTime()));
            billPaymentRepository.save(billPayment);
        }
        else
        {
            updateBillPaymentStatus(billPayment);
        }
    }

    public void updateBillPaymentStatus(BillPayment billPayment){
        billPayment.setStatus(BillPayment.Status.COMPLETED);
        billPaymentRepository.save(billPayment);
    }
}
