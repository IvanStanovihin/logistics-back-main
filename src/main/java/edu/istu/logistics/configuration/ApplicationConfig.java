package edu.istu.logistics.configuration;

import edu.istu.logistics.hopper.model.HopperOrder;

import java.io.IOException;
import java.util.Properties;

public class ApplicationConfig {

    public static HopperOrder START_ORDER;
    private static Properties props;


    public static void initializeConfig(){
        props = new Properties();
        try {
            //load a properties file from class path, inside static method
            props.load(ApplicationConfig.class.getClassLoader().getResourceAsStream("application.properties"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        START_ORDER = createStartOrder();
    }


    private static HopperOrder createStartOrder(){
        String address = props.getProperty("start.point.address");
        double latitude = Double.parseDouble(props.getProperty("start.point.latitude"));
        double longitude = Double.parseDouble(props.getProperty("start.point.longitude"));
        return new HopperOrder(1000L, address, latitude, longitude, 0.0);
    }
}
