package edu.sjsu.cmpe202.banking_system.payee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PayeeService {

    @Autowired
    private PayeeRepository payeeRepository;
    public List<Payee> getAllPayees(){
        List<Payee> payees = new ArrayList<>();
        payeeRepository.findAll()
                .forEach(payees::add);
        return payees;
    }

    public void addPayee(Payee payee){
        payeeRepository.save(payee);
    }

    public void deletePayee(Integer id) {
        payeeRepository.deleteById(id);
    }
}
