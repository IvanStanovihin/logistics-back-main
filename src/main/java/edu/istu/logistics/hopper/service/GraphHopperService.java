package edu.istu.logistics.hopper.service;


import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.Profile;
import edu.istu.logistics.hopper.hopperException.RouteErrorException;
import edu.istu.logistics.hopper.model.HopperOrder;

import java.util.Locale;

public class GraphHopperService {

    private GraphHopper hopper;

    public GraphHopperService(String pathToMapFile){
        this.hopper = new GraphHopper();
        this.hopper.setOSMFile(pathToMapFile);
        this.hopper.setGraphHopperLocation("src/main/resources/cache-for-hopper");
        hopper.setProfiles(new Profile("car")
                .setVehicle("car").setWeighting("fastest").setTurnCosts(false));
        hopper.importOrLoad();
    }

    public double getDistance(HopperOrder point1, HopperOrder point2) throws RouteErrorException {
        GHRequest request = new GHRequest(point1.getLongitude(), point1.getLatitude(),
                point2.getLongitude(), point2.getLatitude()).setProfile("car")
                .setLocale(Locale.getDefault());
        GHResponse response = hopper.route(request);
        if (response.hasErrors()){
            throw new RouteErrorException("Route calculation response contains errors", response.getErrors());
        }
        ResponsePath path = response.getBest();
        return path.getDistance();
    }


}
