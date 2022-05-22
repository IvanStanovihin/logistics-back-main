package edu.istu.logistics.hopper.hopperException;

import java.util.List;

public class RouteErrorException extends Exception{

    private List<Throwable>errors;

    public RouteErrorException(String msg, List<Throwable>routingErrors){
        super(msg);
        this.errors = routingErrors;
    }

    public List<Throwable>hetErrors(){
        return errors;
    }
}
