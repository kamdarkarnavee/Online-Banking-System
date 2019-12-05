package edu.sjsu.cmpe202.banking_system.account_creation.saving_account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.sjsu.cmpe202.banking_system.user.User;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.Date;

@Entity
@Table(name = "saving_account")
@Qualifier("SavingAccount")
public class SavingAccount {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long saving_account_no;
    //private long user_id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @DecimalMin("0.00")
    private double balance;
    private Date account_creation_date;
    private boolean account_status;

    public long getSaving_account_no() {
        return saving_account_no;
    }

    public void setSaving_account_no(long saving_account_no) {
        this.saving_account_no = saving_account_no;
    }

    //public long getUser_id() {
    //    return user_id;
    //}

    //public void setUser_id(long user_id) {
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
