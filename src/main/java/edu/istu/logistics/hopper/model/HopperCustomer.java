package edu.istu.logistics.hopper.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class HopperCustomer {

    public static String TYPE_NAME = "Customer";
    private Long id;
    private String name;
    private String phoneNumber;

    public HopperCustomer(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static String getTypeName() {
        return TYPE_NAME;
    }

    public static void setTypeName(String typeName) {
        TYPE_NAME = typeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
