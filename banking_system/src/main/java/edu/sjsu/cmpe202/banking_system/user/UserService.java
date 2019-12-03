package edu.sjsu.cmpe202.banking_system.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
     * @return
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

}

