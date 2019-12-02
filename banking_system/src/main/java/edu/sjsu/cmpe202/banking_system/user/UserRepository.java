package edu.sjsu.cmpe202.banking_system.user;

import org.springframework.data.repository.CrudRepository;

// This is AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByUsernameAndPassword (String username, String password);
}
