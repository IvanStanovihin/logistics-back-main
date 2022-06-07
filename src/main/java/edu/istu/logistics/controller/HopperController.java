package edu.istu.logistics.controller;

import edu.istu.logistics.algorithm.annealing.AnnealingManager;
import edu.istu.logistics.algorithm.branchAndBound.BranchAndBoundService;
import edu.istu.logistics.hopper.model.OrderList;
import edu.istu.logistics.hopper.model.RouteRequest;
import edu.istu.logistics.hopper.model.RouteResponse;
import edu.istu.logistics.hopper.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/hopper")
public class HopperController {

    @Autowired
    private RoutingService routingService;

    @Autowired
    private BranchAndBoundService branchAndBoundService;

    @PostMapping("/calculateOrderLists")
    public ResponseEntity<?>calculateRoute(@RequestBody RouteRequest request){
        System.out.println("Received: " + request);
        ArrayList<OrderList> resultOrderLists = routingService.calculateRoute(request.getRawOrders(), request.getDrivers());
        resultOrderLists.forEach(OrderList::addStartPoint);
        OrderList[] orderLists = new OrderList[resultOrderLists.size()];
        orderLists = resultOrderLists.toArray(orderLists);
        RouteResponse response = new RouteResponse(orderLists);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/branchAndBound")
    public ResponseEntity<?>calculateBranchAndBound(@RequestBody RouteRequest request){
        System.out.println("Received request: " + request);
        OrderList[] orderLists = branchAndBoundService.calculateBranchAndBounds(request);
        return ResponseEntity.ok(orderLists);
    }

    @PostMapping("/routeAnnealing")
    public ResponseEntity<?>routeWithAnnealing(@RequestBody RouteRequest request){
        System.out.println("Received: " + request);
        ArrayList<OrderList> resultOrderLists = routingService.calculateRoute(request.getRawOrders(), request.getDrivers());
        AnnealingManager annealingManager = new AnnealingManager(resultOrderLists);
        ArrayList<OrderList>annealedOrderLists = annealingManager.doAnnealing();
        annealedOrderLists.forEach(OrderList::addStartPoint);
        OrderList[] routingResult = new OrderList[annealedOrderLists.size()];
        routingResult = annealedOrderLists.toArray(routingResult);
        RouteResponse response = new RouteResponse(routingResult);
        return ResponseEntity.ok(response);
    }
}
