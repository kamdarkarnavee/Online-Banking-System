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
    private String bank_name;

    @ValidRoutingNumber
    private long routing_number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public long getRouting_number() {
        return routing_number;
    }

    public void setRouting_number(long routing_number) {
        this.routing_number = routing_number;
    }
}
