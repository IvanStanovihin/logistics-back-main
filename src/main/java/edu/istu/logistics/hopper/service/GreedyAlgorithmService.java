package edu.istu.logistics.hopper.service;

import edu.istu.logistics.hopper.model.HopperOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GreedyAlgorithmService {

    private Set<Integer>visitedPoints;
    private double summaryDistance = 0;

    public GreedyAlgorithmService(){
        this.visitedPoints = new HashSet<>();
        visitedPoints.add(0);
    }

    public ArrayList<HopperOrder> calculateRoute(HopperOrder[] rawOrders, double[][]distanceMatrix){
        ArrayList<HopperOrder>sortedOrders = new ArrayList<HopperOrder>();
        sortedOrders.add(rawOrders[0]);
        int counter = 0;
        int index = 0;
        while (counter < rawOrders.length - 1){
            double []distances = distanceMatrix[index];
            int minDistanceIndex = getMinDistanceIndex(distances);
            index = minDistanceIndex;
            summaryDistance += distances[minDistanceIndex];
            visitedPoints.add(minDistanceIndex);
            sortedOrders.add(rawOrders[minDistanceIndex]);
            counter++;
        }
        return sortedOrders;
    }

    /**
     * Метод возвращает индекс минимального не посещённого ранее элемента в массиве. Минимальный элемент - это
     * точка, которую нужно следующей добавить в маршрутный лист.
     * @param distances
     * @return
     */
    private int getMinDistanceIndex(double[]distances){
        int minDistanceIndex = -1;
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < distances.length; i++){
            if (!visitedPoints.contains(i) && distances[i] < minDistance && distances[i] != 0){
                minDistance = distances[i];
                minDistanceIndex = i;
            }
        }
        System.out.println("\n\nMin distance index: " + minDistanceIndex + "; From matrix: ");
        Arrays.stream(distances).forEach(d -> System.out.print(d + " "));
        return minDistanceIndex;
    }

    public Set<Integer> getVisitedPoints() {
        return visitedPoints;
    }

    public void setVisitedPoints(Set<Integer> visitedPoints) {
        this.visitedPoints = visitedPoints;
    }

    public double getSummaryDistance() {
        return summaryDistance;
    }

    public void setSummaryDistance(double summaryDistance) {
        this.summaryDistance = summaryDistance;
    }
}
