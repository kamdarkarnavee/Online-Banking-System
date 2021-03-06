package edu.sjsu.cmpe202.banking_system.account_creation.saving_account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.sjsu.cmpe202.banking_system.constraint.ValidAccountNumber;
import edu.sjsu.cmpe202.banking_system.user.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.util.Date;

@Entity
@Table(name = "saving_account")
@Qualifier("SavingAccount")
public class SavingAccount {

    @ValidAccountNumber
    @Id
    private long saving_account_no;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @DecimalMin("0.00")
    private double balance;
    private Date account_creation_date;

    public long getSaving_account_no() {
        return saving_account_no;
    }

    public void setSaving_account_no(long saving_account_no) {
        this.saving_account_no = saving_account_no;
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
