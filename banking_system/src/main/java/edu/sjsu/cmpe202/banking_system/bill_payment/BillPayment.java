package edu.sjsu.cmpe202.banking_system.bill_payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.sjsu.cmpe202.banking_system.payee.Payee;
import edu.sjsu.cmpe202.banking_system.transactions.Transactions;
import edu.sjsu.cmpe202.banking_system.user.User;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "billpayment")
public class BillPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payee_id")
    private Payee payee;

    private String transaction_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    private long from_account;
    private long to_account;

    @DecimalMin("1.00")
    private double transaction_amount;

    @DecimalMin("0.00")
    private double balance;

    private String transaction_details;

    private String transaction_description;

    enum Period{
        WEEKLY,
        MONTHLY
    }
    private Period period;

    enum DayOfWeek{
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }

    private DayOfWeek dayOfWeek;

    private int dateOfMonth;

    private String final_date  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    enum Status{
        PENDING,
        COMPLETED
    }

    private Status status;

    public User getUser_id() {
        return user;
    }

    public void setUser_id(User user_id) {
        this.user = user_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Payee getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee = payee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDateOfMonth() {
        return dateOfMonth;
    }

    public void setDateOfMonth(int dateOfMonth) {
        this.dateOfMonth = dateOfMonth;
    }

    public Date getFinal_date() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(final_date));
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return cal.getTime();
    }

    public void setFinal_date(String final_date) {
        this.final_date = final_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
