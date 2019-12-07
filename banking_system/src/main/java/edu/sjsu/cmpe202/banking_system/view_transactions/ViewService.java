package edu.sjsu.cmpe202.banking_system.view_transactions;

import edu.sjsu.cmpe202.banking_system.transactions.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@Service
public class ViewService {

    @Autowired
    ViewRepository viewRepository;

    public List<Transactions> viewTransactions(long account_no, String transaction_type, String start_date, String end_date) throws ParseException {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
//        Date date1 = sd.parse(start_date);
//        Date date2 = sd.parse(end_date);

        Date date1 = null, date2 = null;
        if(start_date.isEmpty()){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            date1 = cal.getTime();
        }else{
            date1 = sd.parse(start_date);
        }

        if(end_date.isEmpty()){
            date2 = new Date();

        }else{
            date2 = sd.parse(end_date);
        }

        long duration = date2.getTime() - date1.getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
        if(diffInDays > 547.86){
            throw new ResponseStatusException(NOT_ACCEPTABLE, "Cannot view transactions beyond 18 months");
        }
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
