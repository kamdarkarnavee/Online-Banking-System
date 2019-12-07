package edu.sjsu.cmpe202.banking_system.view_transactions;

import edu.sjsu.cmpe202.banking_system.transactions.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class ViewService {

    @Autowired
    ViewRepository viewRepository;

    public List<Transactions> viewTransactions(long account_no, String transaction_type, String start_date, String end_date) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sd.parse(start_date);
        Date date2 = sd.parse(end_date);
        List<Transactions> transactionsList = new ArrayList<>((viewRepository.findByTransaction_dateBetween(date1, date2)));

//        List<Transactions> transactionsList = new ArrayList<>((Collection<? extends Transactions>) viewRepository.findAll());

        List<Transactions> transactions = new ArrayList<>();
        boolean isTransactionTypeSet = transaction_type.isEmpty();
        for(Transactions t : transactionsList){
            if(t.getFrom_account() == account_no || t.getTo_account() == account_no){
                if(t.getTo_account() == account_no && !t.getTransaction_details().equals("declined"))
                    t.setTransaction_details("credited");
                transactions.add(t);
            }

            if(!isTransactionTypeSet){
                if(!t.getTransaction_details().toLowerCase().equals(transaction_type.toLowerCase()))
                    transactions.remove(t);
            }
        }
        return transactions;
    }
}
