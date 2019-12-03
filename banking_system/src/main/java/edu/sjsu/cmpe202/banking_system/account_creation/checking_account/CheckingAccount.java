package edu.sjsu.cmpe202.banking_system.account_creation.checking_account;


import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.sjsu.cmpe202.banking_system.user.User;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "checking_account")
@Qualifier("CheckingAccount")
public class CheckingAccount {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int checking_account_no;
   // private int user_id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @DecimalMin("0.00")
    private double balance;

    private Date account_creation_date;
    private boolean account_status;

    public int getChecking_account_no() {
        return checking_account_no;
    }

    public void setChecking_account_no(int checking_account_no) {
        this.checking_account_no = checking_account_no;
    }

    //public int getUser_id() {
    //    return user_id;
    //}

    //public void setUser_id(int user_id) {
    //    this.user_id = user_id;
    //}

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

    public boolean getAccount_status() {
        return account_status;
    }

    public void setAccount_status(boolean account_status) {
        this.account_status = account_status;
    }
}
