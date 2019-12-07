package edu.sjsu.cmpe202.banking_system.recurring_transfer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.sjsu.cmpe202.banking_system.constraint.ValidAccountNumber;
import edu.sjsu.cmpe202.banking_system.user.User;

@Entity
@Table(name = "recurringtransfer")
public class RecurringTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    private String transaction_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    @ValidAccountNumber
    private long from_account;
    
    @ValidAccountNumber
    private long to_account;

    @DecimalMin("1.00")
    private double transaction_amount;

    @DecimalMin("0.00")
    private double balance;

    @NotEmpty(message="transaction_details cannot be empty or null")
    @Size(min = 2, max = 50, message = "transaction_details must be between 2 and 50 characters")
    private String transaction_details;

    @NotEmpty(message="transaction_description cannot be empty or null")
    @Size(min = 2, max = 50, message = "transaction_description must be between 2 and 50 characters")
    private String transaction_description;

    enum Period{
        WEEKLY,
        MONTHLY
    }
    private Period period;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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



    private String final_date  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    enum Status{
        PENDING,
        COMPLETED
    }

    private Status status;

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

	public String getTransaction_details() {
		return transaction_details;
	}

	public void setTransaction_details(String transaction_details) {
		this.transaction_details = transaction_details;
	}

}
