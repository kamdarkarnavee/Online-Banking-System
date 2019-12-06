package edu.sjsu.cmpe202.banking_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@SpringBootApplication//(scanBasePackages={"edu.sjsu.cmpe202.banking_system.account_creation.checking_account", "edu.sjsu.cmpe202.banking_system.account_creation.saving_account","edu.sjsu.cmpe202.banking_system.transactions"})
@EnableScheduling
public class BankingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingSystemApplication.class, args);
    }

}
