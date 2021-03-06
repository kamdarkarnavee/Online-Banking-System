package edu.sjsu.cmpe202.banking_system.payee;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PayeeController {

    @Autowired
    private PayeeService payeeService;

    @RequestMapping("/{user_id}/allpayees")
    public List<Payee> getPayeesbyUserId(@PathVariable(value = "user_id") int user_id){
        return  payeeService.getAllPayees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payee> getUser (@PathVariable Integer id) {
        Payee foundPayee = payeeService.getPayeeById(id);
        if (foundPayee == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundPayee);
        }
    }

    @PostMapping(value = "/{user_id}/approvepayee")
    public boolean approvePayee(@PathVariable(value = "user_id") int user_id){
        boolean status = payeeService.approvePayee(user_id);
        return status;
    }


    @PostMapping(value = "/{user_id}/addpayee")
    public Payee addPayee(@Valid @PathVariable(value = "user_id") int user_id,@RequestBody Payee payee) {
        payeeService.addPayee(user_id,payee);
        return payee;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{user_id}/deletepayee/{id}")
    public void deletePayee(@PathVariable(value = "user_id") int user_id,@PathVariable Integer id){
        payeeService.deletePayee(id);
    }

}
