package edu.istu.logistics.hopper.model;

public class RouteResponse {

    private OrderList[]routeLists;

    public RouteResponse(OrderList[] routeLists) {
        this.routeLists = routeLists;
    }

    public OrderList[] getRouteLists() {
        return routeLists;
    }

    public void setRouteLists(OrderList[] routeLists) {
        this.routeLists = routeLists;
    }
}
