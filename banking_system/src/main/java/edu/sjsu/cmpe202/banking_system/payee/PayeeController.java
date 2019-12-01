package edu.sjsu.cmpe202.banking_system.payee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PayeeController {

    @Autowired
    private PayeeService payeeService;

    @RequestMapping("/payee/addpayee")
    public List<Payee> getAllPayees(){
        return  payeeService.getAllPayees();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/payee/addpayee")
    public void addPayee(@RequestBody Payee payee) {
        payeeService.addPayee(payee);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/payee/{id}")
    public void deletePayee(@PathVariable Integer id){
        payeeService.deletePayee(id);
    }
}
