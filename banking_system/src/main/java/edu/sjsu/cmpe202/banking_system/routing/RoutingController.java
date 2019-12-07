package edu.sjsu.cmpe202.banking_system.routing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RoutingController {

    @Autowired
    private RoutingService routingService;

    @PostMapping(value = "/routing_table")
    public Routing addRoutingDetails(@Valid @RequestBody Routing routing){
        routingService.addRoutingDetails(routing);
        return routing;
    }



}
