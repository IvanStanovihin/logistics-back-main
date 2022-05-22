package edu.istu.logistics.hopper.model;

import java.sql.Driver;

public class Truck {

    private Long id;
    private double maxWeight;
    private String modelName;

    public Truck(){}

    public Truck(double maxWeight, String modelName) {
        this.id = id;
        this.maxWeight = maxWeight;
        this.modelName = modelName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
