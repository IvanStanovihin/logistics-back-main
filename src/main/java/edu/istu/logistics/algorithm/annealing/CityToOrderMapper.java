package edu.istu.logistics.algorithm.annealing;

import edu.istu.logistics.hopper.model.HopperOrder;

import java.util.ArrayList;

public class CityToOrderMapper {

    public static ArrayList<HopperOrder>getOrders(Travel travel){
        ArrayList<HopperOrder>orders = new ArrayList<>();
        ArrayList<City>cities = travel.getTravel();
        for (City city : cities){
            orders.add(city.getOrder());
        }
        return orders;
    }
}
