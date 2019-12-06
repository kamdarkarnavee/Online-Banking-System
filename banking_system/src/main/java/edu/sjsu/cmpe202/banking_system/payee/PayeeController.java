package edu.sjsu.cmpe202.banking_system.payee;

import edu.sjsu.cmpe202.banking_system.account_creation.checking_account.CheckingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PayeeController {

    @Autowired
    private PayeeService payeeService;

    @RequestMapping("/payee/allpayees")
    public List<Payee> getAllPayees(){
        return  payeeService.getAllPayees();
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/payee/addpayee")
//    public void addPayee(@RequestBody Payee payee) {
//        payeeService.addPayee(payee);
//    }

    @PostMapping(value = "/{user_id}/addpayee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Payee addPayee(@PathVariable(value = "user_id") int user_id, @RequestBody Payee payee) {
        return payeeService.addPayee(user_id, payee);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/payee/{id}")
    public void deletePayee(@PathVariable Integer id){
        payeeService.deletePayee(id);
    }
}
