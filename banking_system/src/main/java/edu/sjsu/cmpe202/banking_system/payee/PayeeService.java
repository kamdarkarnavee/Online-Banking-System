package edu.sjsu.cmpe202.banking_system.payee;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccount;
import edu.sjsu.cmpe202.banking_system.user.User;
import edu.sjsu.cmpe202.banking_system.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PayeeService {

    @Autowired
    private PayeeRepository payeeRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Payee> getAllPayees(){
        List<Payee> payees = new ArrayList<>();
        payeeRepository.findAll()
                .forEach(payees::add);
        return payees;
    }

   // public void addPayee(Payee payee){
        //payeeRepository.save(payee);
    //}
    /**
     * Add a new payee
     */
    public Payee addPayee(int user_id, Payee payee) {
        Optional<User> user = userRepository.findById(user_id);
        if (!user.isPresent()) {
            throw  new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not exist");
        }

        //tie User to Payee
        payee.setUser(user.get());
        Payee newPayee = payeeRepository.save(payee);

        //tie CheckingAccount to User
        user.get().setPayee(newPayee);

        return newPayee;
    }

    public void deletePayee(Integer id) {
        payeeRepository.deleteById(id);
    }
}
