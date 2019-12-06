package edu.sjsu.cmpe202.banking_system.user;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import edu.sjsu.cmpe202.banking_system.bill_payment.BillPayment;
import edu.sjsu.cmpe202.banking_system.constraint.ValidDate;
import edu.sjsu.cmpe202.banking_system.constraint.ValidPassword;
import edu.sjsu.cmpe202.banking_system.constraint.ValidPhoneNumber;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccount;
import edu.sjsu.cmpe202.banking_system.account_creation.saving_account.SavingAccount;
import edu.sjsu.cmpe202.banking_system.payee.Payee;
import org.hibernate.annotations.ColumnDefault;

import java.util.*;


@Entity // This tells Hibernate to make a table out of this class
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @NotEmpty(message="First name cannot be empty or null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotEmpty(message="Last name cannot be empty or null")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Column(unique=true)
    @NotEmpty(message="Username cannot be empty or null")
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    private String username;

    @ValidPassword
    private String password;

    @NotEmpty(message="City cannot be empty or null")
    @Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
    private String city;

    @NotEmpty(message="State cannot be empty or null")
    @Size(min = 2, max = 2, message = "State must be 2 characters")
    private String state;

    @NotEmpty(message="Address cannot be empty or null")
    @Size(min = 2, max = 50, message = "Address must be between 2 and 50 characters")
    private String address;

    @ValidDate
    private String dateOfBirth;

    @Column(unique=true)
    @Email(message="Email must be valid")
    private String email;

    @ValidPhoneNumber
    private String phoneNumber;

    @Column(columnDefinition="tinyint(1) default 1")
    @NotNull
    private boolean admin;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private CheckingAccount checkingAccount;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private SavingAccount savingAccount;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Payee> payee;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<BillPayment> billpayment;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
    }

    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public SavingAccount getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }

    public Set<Payee> getPayee() {
        return payee;
    }

    public void setPayee(Payee payee) {
        this.payee.add(payee);
    }

    public Set<BillPayment> getBillpayment() {
        return billpayment;
    }

    public void setBillpayment(BillPayment billpayment) {
        this.billpayment.add(billpayment);
    }

}