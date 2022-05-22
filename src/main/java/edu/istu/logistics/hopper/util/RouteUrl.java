package edu.istu.logistics.hopper.util;

import edu.istu.logistics.hopper.model.HopperOrder;

public class RouteUrl {

    private String address = "localhost";
    private String port = "8989";
    private final String protocol = "http";
    private final String method = "route";
    private final String type = "json";
    private String latitude1;
    private String longitude1;
    private String latitude2;
    private String longitude2;
    private String locale = "ru";
    private String key = "";
    private String elevation="false";
    private String profile="car";

    public RouteUrl(HopperOrder point1, HopperOrder point2){
        this.latitude1 = Double.toString(point1.getLatitude());
        this.longitude1 = Double.toString(point1.getLongitude());
        this.latitude2 = Double.toString(point2.getLatitude());
        this.longitude2 = Double.toString(point2.getLongitude());
    }

    public String getUrl(){
        String url = new StringBuilder().append(protocol).append("://")
                .append(address).append(":").append(port).append("/")
                .append(method).append("?")
                .append("point=").append(latitude1).append(",").append(longitude1).append("&")
                .append("point=").append(latitude2).append(",").append(longitude2).append("&")
                .append("type=").append(type).append("&")
                .append("locale=").append(locale).append("&")
                .append("key=").append(key).append("&")
                .append("elevation=").append(elevation).append("&")
                .append("profile=").append(profile).toString();
        return url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(String port){
        this.port = port;
    }

    public String getMethod() {
        return method;
    }

    public String getType() {
        return type;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
