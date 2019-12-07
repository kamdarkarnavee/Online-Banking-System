package edu.sjsu.cmpe202.banking_system.payee;

import edu.sjsu.cmpe202.banking_system.routing.Routing;
import edu.sjsu.cmpe202.banking_system.routing.RoutingRepository;
import edu.sjsu.cmpe202.banking_system.user.User;
import edu.sjsu.cmpe202.banking_system.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

    public List<Payee> getAllPayees() {
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
            throw new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not exist");
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
        } else {
            return null;
        }
    }

    public boolean approvePayee(int user_id) {
        Optional<User> user = userRepository.findById(user_id);

        if (!user.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not exist");
        }
        if (user.get().getAdmin()) {
            List<Payee> unapprovedPayees = getUnapprovedPayees();
            if (unapprovedPayees.size() == 0) {
                throw new ResponseStatusException(NOT_FOUND, "You don't have payees to approve");
            } else {

                    for (int i = 0; i < unapprovedPayees.size(); i++) {
                        Routing r = routingRepository.findByBankName(unapprovedPayees.get(i).getBank_name());
                        if (unapprovedPayees.get(i).getRouting_number() == r.getRoutingNumber()) {
                            unapprovedPayees.get(i).setApproved(true);
                            payeeRepository.save(unapprovedPayees.get(i));
                        }
                        else {
                            System.out.println("Routing Number does not match for payee .Deleting payee" + unapprovedPayees.get(i).getId());
                            payeeRepository.deleteById(unapprovedPayees.get(i).getId());
                        }

                    }
                    return true;
            }


        } else {
            throw new ResponseStatusException(BAD_REQUEST, "You don't have admin privileges.");

        }
    }

    public List<Payee> getUnapprovedPayees() {
        List<Payee> unapprovedpayee = new ArrayList<>();
        unapprovedpayee.addAll((Collection<? extends Payee>) payeeRepository.findAll());
        List<Payee> payees = new ArrayList<>();
        for (Payee p : unapprovedpayee) {
            if (!p.isApproved()) {
                payees.add(p);
            }
        }
        return payees;
    }

    public List<Payee> getPayeesbyUserId(int user_id) {
        List<Payee> payees = new ArrayList<>();
        payees.addAll((Collection<? extends Payee>) payeeRepository.findAll());
        List<Payee> myPayees = new ArrayList<>();
        for (Payee p : payees) {
            if (p.getUser().getId() == user_id) {
                myPayees.add(p);
            }
        }
        return myPayees;
    }
}
