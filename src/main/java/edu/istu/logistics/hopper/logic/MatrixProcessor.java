package edu.istu.logistics.hopper.logic;

import edu.istu.logistics.hopper.model.HopperOrder;
import edu.istu.logistics.hopper.util.RouteResponseParser;
import edu.istu.logistics.hopper.util.RouteUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;

public class MatrixProcessor {


    public MatrixProcessor(){

    }

    public double[][] getDistanceMatrix(ArrayList<HopperOrder> order){
        int matrixSize = order.size();
        double[][]distanceMatrix = new double[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++){
            for (int j = 0; j < matrixSize; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                } else {
                    String distance = getDistance(order.get(i), order.get(j));;
                    distanceMatrix[i][j] = Float.parseFloat(distance);
                }
            }
        }
        return distanceMatrix;
    }

    public String getDistance(HopperOrder point1, HopperOrder point2){
        OkHttpClient client = new OkHttpClient();
        String distance = null;
        try {
            RouteUrl routeUrl = new RouteUrl(point1, point2);
            String strGetUrl = routeUrl.getUrl();
            Request request = new Request.Builder().url(strGetUrl).get().build();
            Response response = client.newCall(request).execute();
            distance = RouteResponseParser.getDistance(response.body().string());
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return distance;
    }

    public static void printMatrix(double[][]distanceMatrix){
        System.out.println("DISTANCE MATRIX: ");
        for (int i = 0; i < distanceMatrix.length; i++){
            System.out.println();
            for (int j = 0; j < distanceMatrix.length; j++){
                System.out.printf("%20s | ", distanceMatrix[i][j]);
            }
        }
        System.out.println();
    }
}
