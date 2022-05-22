package edu.istu.logistics.hopper.model;

import java.sql.Driver;
import java.util.Arrays;

public class RouteRequest {

    private HopperOrder[] rawOrders;
    private HopperDriver[]drivers;

    public RouteRequest(HopperOrder[] rawOrders, HopperDriver[] drivers) {
        this.rawOrders = rawOrders;
        this.drivers = drivers;
    }

    public HopperOrder[] getRawOrders() {
        return rawOrders;
    }

    public void setRawOrders(HopperOrder[] rawOrders) {
        this.rawOrders = rawOrders;
    }

    public HopperDriver[] getDrivers() {
        return drivers;
    }

    public void setDrivers(HopperDriver[] drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "RouteRequest{" +
                "rawOrders=" + Arrays.toString(rawOrders) +
                ", drivers=" + Arrays.toString(drivers) +
                '}';
    }
}
