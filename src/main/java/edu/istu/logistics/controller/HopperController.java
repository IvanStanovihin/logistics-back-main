package edu.istu.logistics.controller;

import edu.istu.logistics.hopper.model.OrderList;
import edu.istu.logistics.hopper.model.RouteRequest;
import edu.istu.logistics.hopper.model.RouteResponse;
import edu.istu.logistics.hopper.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/hopper")
public class HopperController {

    @Autowired
    private RoutingService routingService;

    @PostMapping("/calculateOrderLists")
    public ResponseEntity<?>calculateRoute(@RequestBody RouteRequest request){
        System.out.println("Received: " + request);
        ArrayList<OrderList> resultOrderLists = routingService.calculateRoute(request.getRawOrders(), request.getDrivers());
        OrderList[] orderLists = new OrderList[resultOrderLists.size()];
        orderLists = resultOrderLists.toArray(orderLists);
        RouteResponse response = new RouteResponse(orderLists);
        return ResponseEntity.ok(response);
    }
}
