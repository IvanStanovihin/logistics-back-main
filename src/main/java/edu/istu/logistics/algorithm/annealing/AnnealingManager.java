package edu.istu.logistics.algorithm.annealing;

import edu.istu.logistics.hopper.model.HopperOrder;
import edu.istu.logistics.hopper.model.OrderList;

import java.util.ArrayList;

public class AnnealingManager {

    private ArrayList<OrderList>orderLists;

    public AnnealingManager(ArrayList<OrderList>orderLists){
        this.orderLists = orderLists;
    }

    public ArrayList<OrderList>doAnnealing(){
        for (OrderList orderList : orderLists){
            annealingOrderRList(orderList);
        }
        return orderLists;
    }

    private OrderList annealingOrderRList(OrderList orderList){
        ArrayList<HopperOrder>ordersInRoute = orderList.getGroupedOrder();
        ArrayList<HopperOrder>annealedOrders = SimulatedAnnealing.simulateAnnealing(10,
                100, 0.9995, ordersInRoute);
        orderList.setGroupedOrder(annealedOrders);
        return orderList;
    }
}
