package edu.istu.logistics.hopper.logic;

import edu.istu.logistics.configuration.ApplicationConfig;
import edu.istu.logistics.hopper.model.HopperDriver;
import edu.istu.logistics.hopper.model.HopperOrder;
import edu.istu.logistics.hopper.model.OrderList;

import java.util.ArrayList;
import java.util.Arrays;

public class WeightService {


    private int driverCounter = 0;

    public WeightService(){

    }

    public ArrayList<OrderList>splitByWeight(HopperDriver[] freeDrivers, ArrayList<HopperOrder> routeForOrders){
        ArrayList<OrderList> allOrderList = new ArrayList<>();
        ArrayList<HopperOrder>ordersForSplitting = new ArrayList<>(routeForOrders);
        ArrayList<HopperDriver>driversForSplitting = new ArrayList<>(Arrays.asList(freeDrivers));
        int pointer = 0;
        while(ordersForSplitting.size() != 0){
            ordersForSplitting.stream().forEach(System.out::println);
            HopperDriver currentDriver = getDriver(freeDrivers);
            OrderList orderList = getOrdersList(currentDriver, ordersForSplitting);
            allOrderList.add(orderList);
        }
        return allOrderList;
    }

    /**
     * Метод составляет один маршрутный лист для водителя. Маршрутный лист заполняется заявка до тех пор, пока машина
     * может вместить груз следующей точки. Если машина заполнена, и при загрузке на следующей точке возникнет перегруз, то
     * маршрутный лист считается завершённым.
     * @param driver
     * @param ordersForSplitting
     * @return
     */
    private OrderList getOrdersList(HopperDriver driver, ArrayList<HopperOrder>ordersForSplitting){
        OrderList orderList = new OrderList();
        double truckWeightLimit = driver.getTruck().getMaxWeight();
        HopperOrder currentOrder = ordersForSplitting.get(0);
        double orderWeight = currentOrder.getCargoWeight();
        double routeWeight = 0;
        while(routeWeight + orderWeight <= truckWeightLimit && ordersForSplitting.size() != 0){
            currentOrder = ordersForSplitting.get(0);
            ordersForSplitting.remove(0);
            routeWeight += orderWeight;
            orderList.addOrder(currentOrder);
            orderWeight = currentOrder.getCargoWeight();
        }
        orderList.setDriver(driver);
        orderList.addOrder(ApplicationConfig.START_ORDER);
        return orderList;
    }

    private HopperDriver getDriver(HopperDriver[] freeDrivers){
        if (driverCounter >= freeDrivers.length){
            driverCounter = 0;
        }
        HopperDriver driver = freeDrivers[driverCounter];
        driverCounter++;
        return driver;
    }
}
