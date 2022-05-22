package edu.istu.logistics.hopper.model;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Collection;

public class OrderList {

    private Long id;
    private ArrayList<HopperOrder> groupedOrder;
    private HopperDriver driver;
    private Double summaryWeight;


    public OrderList() {
    }

    public OrderList(Long id, ArrayList<HopperOrder> groupedOrder, HopperDriver driver, Double summaryWeight) {
        this.id = id;
        this.groupedOrder = groupedOrder;
        this.driver = driver;
        this.summaryWeight = summaryWeight;
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
}
