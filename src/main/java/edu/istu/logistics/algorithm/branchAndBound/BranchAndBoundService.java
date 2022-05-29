package edu.istu.logistics.algorithm.branchAndBound;


import edu.istu.logistics.hopper.logic.MatrixProcessor;
import edu.istu.logistics.hopper.logic.WeightService;
import edu.istu.logistics.hopper.model.HopperDriver;
import edu.istu.logistics.hopper.model.HopperOrder;
import edu.istu.logistics.hopper.model.OrderList;
import edu.istu.logistics.hopper.model.RouteRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

@Service
public class BranchAndBoundService {

    private BranchAndBoundEngine algorithmEngine;
    private MatrixProcessor matrixProcessor;
    private WeightService weightService;

    @PostConstruct
    public void init(){
        algorithmEngine = new BranchAndBoundEngine();
        matrixProcessor = new MatrixProcessor();
        weightService = new WeightService();
    }

    public OrderList[] calculate(RouteRequest request){
        HopperOrder[] rawOrders = request.getRawOrders();
        HopperDriver[] freeDrivers = request.getDrivers();
        double[][] distanceMatrix = matrixProcessor.getDistanceMatrix(new ArrayList(Arrays.asList(rawOrders)));
        Stack<Integer>optimizedPath = algorithmEngine.start(distanceMatrix);
        ArrayList<HopperOrder>optimizedOrders = moveOrders(rawOrders, optimizedPath);
        ArrayList<OrderList>optimizedOrderLists = weightService.splitByWeight(freeDrivers, optimizedOrders);
        OrderList[]orderLists = new OrderList[optimizedOrderLists.size()];
        return optimizedOrderLists.toArray(orderLists);

    }

    private ArrayList<HopperOrder>moveOrders(HopperOrder[]rawOrders, Stack<Integer>optimizedPath){
        ArrayList<HopperOrder>resultOrders = new ArrayList<>();
        System.out.println("Stack: " + optimizedPath);
        for (Integer point : optimizedPath){
            System.out.println("Point:" + point);
            resultOrders.add(rawOrders[point - 1]);
        }
        System.out.println("ResultOrders: " + resultOrders);
        return resultOrders;
    }

}
