package edu.sjsu.cmpe202.banking_system.routing;

import org.springframework.beans.factory.annotation.Autowired;

public class RoutingService {

    @Autowired
    private RoutingRepository routingRepository;

    public Routing addRoutingDetails(Routing routing) {
        routingRepository.save(routing);
        return routing;
    }

}
