package edu.sjsu.cmpe202.banking_system.user;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Get all users
     * @return all users
     */
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    /**
     * Get specific user by id
     * @param id user id
     * @return user with specific id
     */
    public User getUserById(Integer id){
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        else {
            return null;
        }
    }

    /**
     * Get specific user by username and checks if password matches
     * @param username user username
     * @return user with specific username
     */
    public User findByUsernameAndPassword (String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    /**
     * add a user
     * @param user user
     */
    public void addUser(User user){
        userRepository.save(user);
    }

    /**
     * update a user
     * @param user user
     * @return boolean
     */
    public Boolean updateUser(User user, Integer id) 
    {
    		User u = userRepository.findById(id).get(); 
    		u.setPhoneNumber(user.getPhoneNumber());
    		u.setEmail(user.getEmail());
    		u.setPassword(user.getPassword());
    		u.setAddress(user.getAddress());
    		u.setCity(user.getCity());
    		u.setState(user.getState());
    		userRepository.save(u);
    		if(userRepository.findById(id).isPresent())
    			return true;
    		else
    			return false;
    }

    /**
     * delete a user
     * @param id user id
     */
    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    /**
     * Transfer Remaining Balance in Checking Account
     */
    public void transferRemainingBalanceinCheckingAccount(int user_id) {
        Optional<User> user = userRepository.findById(user_id);
        double balance = 0;
        if (!user.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not exist");
        }
        if (user.get().getAdmin()) {
            if (user.get().getCheckingAccount() != null) {
                balance = user.get().getCheckingAccount().getBalance();
                user.get().getCheckingAccount().setBalance(0.00);
                userRepository.save(user.get());
                throw new ResponseStatusException(OK, "Account remaining balance of $" + balance + " will be sent to your home in a check.");
            } else {
                throw new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not have a checking account");
            }
        } else {
            throw new ResponseStatusException(BAD_REQUEST, "You don't have admin privileges.");
        }
    }


    /**
     * Transfer Remaining Balance in Saving Account
     */
    public void transferRemainingBalanceinSavingAccount(int user_id) {
        Optional<User> user = userRepository.findById(user_id);
        double balance = 0;
        if (!user.isPresent()) {
            throw new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not exist");
        }
        if (user.get().getAdmin()) {
            if (user.get().getSavingAccount() != null) {
                balance = user.get().getSavingAccount().getBalance();
                user.get().getSavingAccount().setBalance(0.00);
                userRepository.save(user.get());
                throw new ResponseStatusException(OK, "Account remaining balance of $" + balance + " will be sent to your home in a check.");
            } else {
                throw new ResponseStatusException(NOT_FOUND, "Author with id " + user_id + " does not have a saving account");
            }
        } else {
            throw new ResponseStatusException(BAD_REQUEST, "You don't have admin privileges.");
        }
    }


}

