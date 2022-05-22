package edu.istu.logistics.hopper.model;


import java.util.ArrayList;
import java.util.Collection;

public class HopperOrderList {

    private Long id;

    private Collection<HopperOrder> groupOrder;
    private Double summaryWeight;


    public HopperOrderList(){
        this.groupOrder = new ArrayList<>();
        this.summaryWeight = 0.0;
    }

    public HopperOrderList(ArrayList<HopperOrder> groupOrder, Double summaryWeight) {
        this.groupOrder = groupOrder;
        this.summaryWeight = summaryWeight;
    }

    public void addOrder(HopperOrder order){
        groupOrder.add(order);
        this.summaryWeight+=order.getCargoWeight();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGroupOrder(Collection<HopperOrder> groupOrder) {
        this.groupOrder = groupOrder;
    }

    public Collection<HopperOrder> getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(ArrayList<HopperOrder> groupOrder) {
        this.groupOrder = groupOrder;
    }

    public Double getSummaryWeight() {
        return summaryWeight;
    }

    public void setSummaryWeight(Double summaryWeight) {
        this.summaryWeight = summaryWeight;
    }

    @Override
    public String toString() {
        return "\nOrdersList{" +
                "groupOrder=" + groupOrder +
                ", summaryWeight=" + summaryWeight +
                '}';
    }
}
