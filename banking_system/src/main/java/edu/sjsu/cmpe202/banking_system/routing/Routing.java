package edu.sjsu.cmpe202.banking_system.routing;

import edu.sjsu.cmpe202.banking_system.constraint.ValidRoutingNumber;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="routing_table")
public class Routing {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @NotEmpty(message="Bank Name cannot be empty or null")
    @Size(min = 2, max = 50, message = "Bank Name must be between 2 and 50 characters")
    private String bankName;

    @ValidRoutingNumber
    private long routingNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public long getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(long routingNumber) {
        this.routingNumber = routingNumber;
    }
}
