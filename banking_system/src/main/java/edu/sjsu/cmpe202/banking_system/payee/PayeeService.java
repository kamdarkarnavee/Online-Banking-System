package edu.sjsu.cmpe202.banking_system.payee;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccount;
import edu.sjsu.cmpe202.banking_system.routing.Routing;
import edu.sjsu.cmpe202.banking_system.routing.RoutingRepository;
import edu.sjsu.cmpe202.banking_system.user.User;
import edu.sjsu.cmpe202.banking_system.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PayeeService {

    @Autowired
    private PayeeRepository payeeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoutingRepository routingRepository;

    public List<Payee> getAllPayees(){
        List<Payee> payees = new ArrayList<>();
        payeeRepository.findAll()
                .forEach(payees::add);
        return payees;
    }

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

    public Payee getPayeeById(Integer id) {
        if (payeeRepository.findById(id).isPresent()) {
            return payeeRepository.findById(id).get();
        }
        else {
            return null;
        }
    }

    public boolean approvePayee(int user_id, Payee payee) {
        Optional<User> user = userRepository.findById(user_id);

        if (!user.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not exist");
        }
        if (user.get().getAdmin()) {
            List<Payee> unapprovedPayees = getUnapprovedPayees();
            if(unapprovedPayees.size() == 0){
                throw new ResponseStatusException(NOT_FOUND, "You don't have payees to approve");
            }
            else
            {
                try
                {
                for(int i=0;i<unapprovedPayees.size();i++){
                    Routing r = routingRepository.findByBank_name(unapprovedPayees.get(i).getBank_name());
                    if (unapprovedPayees.get(i).getRouting_number() == r.getRouting_number()) {
                        payeeRepository.save(unapprovedPayees.get(i));
                    }
                    else
                    {
                        throw new ResponseStatusException(BAD_REQUEST, "Routing number is wrong for BANK : " +unapprovedPayees.get(i).getBank_name() );
                    }

                }
                return true;
            }
                catch (Exception e){System.out.println("Wrong Routing Number");};
            }


        }
        else {
            throw new ResponseStatusException(BAD_REQUEST, "You don't have admin privileges.");

        }
        return false;
    }

    public List<Payee> getUnapprovedPayees()
    {
        List<Payee> unapprovedpayee = new ArrayList<>();
        unapprovedpayee.addAll((Collection<? extends Payee>) payeeRepository.findAll());
        List<Payee> payees = new ArrayList<>();
        for(Payee p : unapprovedpayee){
            if(!p.isApproved()){
                payees.add(p);
            }
        }
        return payees;
    }

}
