package edu.sjsu.cmpe202.banking_system.bill_payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.sjsu.cmpe202.banking_system.transactions.Transactions;
import edu.sjsu.cmpe202.banking_system.user.User;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "billpayment")
public class BillPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id;

    private Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private String transaction_date = formatter.format(date);


    private long from_account;

    private long to_account;

    @DecimalMin("1.00")
    private double transaction_amount;

    @DecimalMin("0.00")
    private double balance;

    private String transaction_details;

    private String transaction_description;

    enum Status{
        PENDING,
        COMPLETED
    }

    private Status status;

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }


    public long getFrom_account() {
        return from_account;
    }

    public void setFrom_account(long from_account) {
        this.from_account = from_account;
    }

    public long getTo_account() {
        return to_account;
    }

    public void setTo_account(long to_account) {
        this.to_account = to_account;
    }

    public double getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(double transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTransaction_details() {
        return transaction_details;
    }

    public void setTransaction_details(String transaction_details) {
        this.transaction_details = transaction_details;
    }

    public String getTransaction_description() {
        return transaction_description;
    }

    public void setTransaction_description(String transaction_description) {
        this.transaction_description = transaction_description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
