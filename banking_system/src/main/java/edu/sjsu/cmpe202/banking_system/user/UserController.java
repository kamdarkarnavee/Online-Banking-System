package edu.sjsu.cmpe202.banking_system.user;

import java.util.HashMap;
import java.util.Map;


import javax.validation.Valid;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController    // Class is a Controller
@RequestMapping("/users") // This means URL's start with /user (after Application path)
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add") //POST requests only
    public User addUser (@Valid @RequestBody User user){
        userService.addUser(user);
        return user;
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser (@PathVariable Integer id) {
        User foundUser = userService.getUserById(id);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundUser);
        }
    }

   @PutMapping("/update/{id}")
    public Boolean updateUser(@RequestBody User user, @PathVariable Integer id) 
   {
	   Boolean result;
	   result = userService.updateUser(user, id);
	   return result;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = "/{id}/checking/transfer_remaining_balance", produces = "application/json")
    public void transferCheckingAccountRemainingBalance(@PathVariable Integer id){
        userService.transferRemainingBalanceinCheckingAccount(id);
    }

    @GetMapping("/{id}/saving/transfer_remaining_balance")
    public void transferSavingAccountRemainingBalance(@PathVariable Integer id){
        userService.transferRemainingBalanceinSavingAccount(id);
    }

    /*** Handle Errors ***/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JDBCException.class)
    @ResponseBody
    public Map<Integer, String> handleValidationExceptions(
            JDBCException ex) {
        Map<Integer, String> errors = new HashMap<>();
        Integer errorCode = ex.getErrorCode();
        String errorMessage = ex.getSQLException().getMessage();
        errors.put(errorCode, errorMessage);
        return errors;
    }

}
