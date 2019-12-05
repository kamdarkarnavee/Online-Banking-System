package edu.sjsu.cmpe202.banking_system.payee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.sjsu.cmpe202.banking_system.user.User;

import javax.persistence.*;

@Entity
@Table(name="payees",uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","account_number_payee"})})
public class Payee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String first_name;
    private String last_name;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private long account_number_payee;
    private long routing_number;
    private boolean is_approved;
    private String payee_description;
    private String bank_name;

    public Payee(){

    }

    public Payee(String first_name, String last_name, long account_number_payee, long routing_number
            , String payee_description,String bank_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.account_number_payee = account_number_payee;
        this.routing_number = routing_number;
        this.payee_description = payee_description;
        this.bank_name = bank_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAccount_number_payee() {
        return account_number_payee;
    }

    public void setAccount_number_payee(long account_number_payee) {
        this.account_number_payee = account_number_payee;
    }

    public double getRouting_number() {
        return routing_number;
    }

    public void setRouting_number(long routing_number) {
        this.routing_number = routing_number;
    }

    public boolean isIs_approved() {
        return is_approved;
    }

    public void setIs_approved(boolean is_approved) {
        this.is_approved = is_approved;
    }
    public String getPayee_description() {
        return payee_description;
    }

    public void setPayee_description(String payee_description) {
        this.payee_description = payee_description;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }
}
