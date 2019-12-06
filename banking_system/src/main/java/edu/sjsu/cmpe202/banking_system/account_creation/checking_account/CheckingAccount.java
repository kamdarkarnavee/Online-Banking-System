package edu.sjsu.cmpe202.banking_system.account_creation.checking_account;


import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.sjsu.cmpe202.banking_system.user.User;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.Date;

@Entity
@Table(name = "checking_account")
@Qualifier("CheckingAccount")
public class CheckingAccount {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long checking_account_no;
   // private int user_id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @DecimalMin("0.00")
    private double balance;

    private Date account_creation_date;

    public long getChecking_account_no() {
        return checking_account_no;
    }

    public void setChecking_account_no(long checking_account_no) {
        this.checking_account_no = checking_account_no;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getAccount_creation_date() {
        return account_creation_date;
    }

    public void setAccount_creation_date(Date account_creation_date) {
        this.account_creation_date = account_creation_date;
    }
}
