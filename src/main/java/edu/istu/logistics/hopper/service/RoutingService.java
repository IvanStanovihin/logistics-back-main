package edu.istu.logistics.hopper.service;

import edu.istu.logistics.hopper.logic.MatrixProcessor;
import edu.istu.logistics.hopper.logic.WeightService;
import edu.istu.logistics.hopper.model.HopperDriver;
import edu.istu.logistics.hopper.model.HopperOrder;
import edu.istu.logistics.hopper.model.OrderList;
import edu.istu.logistics.hopper.model.Truck;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class RoutingService {

    private MatrixProcessor matrixProcessor;
    private WeightService weightService;

    public RoutingService(){
        this.matrixProcessor = new MatrixProcessor();
        this.weightService = new WeightService();
    }

    public ArrayList<OrderList> calculateRoute(HopperOrder[]rawOrder, HopperDriver[] drivers){
        GreedyAlgorithmService greedyService = new GreedyAlgorithmService();
        ArrayList<HopperOrder>points = new ArrayList<>(Arrays.asList(rawOrder));
        double[][] distanceMatrix = matrixProcessor.getDistanceMatrix(points);
        MatrixProcessor.printMatrix(distanceMatrix);
        ArrayList<HopperOrder>greedyOrders = greedyService.calculateRoute(rawOrder, distanceMatrix);
        greedyOrders.stream().forEach(System.out::println);
        System.out.println("Суммарное расстояние: " + greedyService.getSummaryDistance());
        ArrayList<OrderList> splitedOrders = weightService.splitByWeight(drivers, greedyOrders);
        System.out.println("Маршрутные листы разбитые по весу: ");
        Stream.of(splitedOrders).forEach(System.out::println);
        return splitedOrders;
    }


    private ArrayList<HopperDriver>getTestDrivers(){
        ArrayList<HopperDriver>testDrivers = new ArrayList<HopperDriver>();
        Truck truck1 = new Truck(1200, "КамАз");
        Truck truck2 = new Truck(1900, "БелАз");
        HopperDriver driver1 = new HopperDriver("Иванов", truck1);
        HopperDriver driver2 = new HopperDriver("Петров", truck1);
        HopperDriver driver3 = new HopperDriver("Сидоров", truck2);
        testDrivers.add(driver1);
        testDrivers.add(driver2);
        testDrivers.add(driver3);
        return testDrivers;
    }

}
