package edu.istu.logistics.hopper.model;

import edu.istu.logistics.configuration.ApplicationConfig;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Collection;

public class OrderList {

    private Long id;
    private ArrayList<HopperOrder> groupedOrder = new ArrayList<>();
    private HopperDriver driver;
    private Double summaryWeight = 0.0;


    public OrderList() {
//        this.groupedOrder.add(ApplicationConfig.START_ORDER);
    }

    public OrderList(ArrayList<HopperOrder>orders, Driver driver) {
        this.groupedOrder.add(ApplicationConfig.START_ORDER);
        groupedOrder.addAll(orders);
    }

    public void addStartPoint(){
        this.groupedOrder.add(0, ApplicationConfig.START_ORDER);
        this.groupedOrder.add(groupedOrder.size() , ApplicationConfig.START_ORDER);
    }

    public OrderList(Long id, ArrayList<HopperOrder> groupedOrder, HopperDriver driver, Double summaryWeight) {
        this.id = id;
        this.groupedOrder = groupedOrder;
        this.driver = driver;
        this.summaryWeight = summaryWeight;
        this.groupedOrder.add(ApplicationConfig.START_ORDER);
    }

    public void addOrder(HopperOrder order){
        summaryWeight+=order.getCargoWeight();
        groupedOrder.add(order);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<HopperOrder> getGroupedOrder() {
        return groupedOrder;
    }

    public void setGroupedOrder(ArrayList<HopperOrder> groupedOrder) {
        this.groupedOrder = groupedOrder;
    }

    public HopperDriver getDriver() {
        return driver;
    }

    public void setDriver(HopperDriver driver) {
        this.driver = driver;
    }

    public Double getSummaryWeight() {
        return summaryWeight;
    }

    public void setSummaryWeight(Double summaryWeight) {
        this.summaryWeight = summaryWeight;
    }

    @Override
    public String toString() {
        return "OrderList{" +
                "id=" + id +
                ", groupedOrder=" + groupedOrder +
                ", driver=" + driver +
                ", summaryWeight=" + summaryWeight +
                '}';
    }
}
