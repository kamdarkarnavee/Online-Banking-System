package edu.sjsu.cmpe202.banking_system.payee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.sjsu.cmpe202.banking_system.bill_payment.BillPayment;
import edu.sjsu.cmpe202.banking_system.constraint.ValidAccountNumber;
import edu.sjsu.cmpe202.banking_system.constraint.ValidRoutingNumber;
import edu.sjsu.cmpe202.banking_system.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="payees",uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","account_number_payee"})})
public class Payee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "payee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private BillPayment billPayment;

    @ValidAccountNumber
    private long account_number_payee;

    @ValidRoutingNumber
    private long routing_number;

    private boolean is_approved;
    @NotEmpty(message="payee_description cannot be empty or null")
    @Size(min = 2, max = 50, message = "payee description must be between 2 and 50 characters")
    private String payee_description;

    @NotEmpty(message="Bank Name cannot be empty or null")
    @Size(min = 2, max = 50, message = "Bank Name must be between 2 and 50 characters")
    private String bank_name;

    public Payee(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getAccount_number_payee() {
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
