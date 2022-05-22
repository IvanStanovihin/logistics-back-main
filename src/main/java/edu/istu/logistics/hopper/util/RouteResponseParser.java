package edu.istu.logistics.hopper.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class RouteResponseParser {


    public static String getDistance(String response){
        JSONObject responseObject = new JSONObject(response);
        JSONArray paths = responseObject.getJSONArray("paths");
        String distance = paths.getJSONObject(0).get("distance").toString();
        return distance;
    }
}
