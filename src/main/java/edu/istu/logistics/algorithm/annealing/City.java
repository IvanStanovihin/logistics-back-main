package edu.istu.logistics.algorithm.annealing;

import edu.istu.logistics.hopper.logic.MatrixProcessor;
import edu.istu.logistics.hopper.model.HopperOrder;
import lombok.Data;

@Data
public class City {

    private int x;
    private int y;
    private HopperOrder order;

    public City() {
        this.x = (int) (Math.random() * 500);
        this.y = (int) (Math.random() * 500);
    }

    public City(HopperOrder order) {
        this.order = order;
    }

    public double distanceToCity(City city) {
        int x = Math.abs(getX() - city.getX());
        int y = Math.abs(getY() - city.getY());
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double distanceToPoint(HopperOrder anotherOrder) {
        MatrixProcessor matrixProcessor = new MatrixProcessor();
        Float distanceToAnother = matrixProcessor.getDistance(order, anotherOrder);
        return distanceToAnother;
    }
}
