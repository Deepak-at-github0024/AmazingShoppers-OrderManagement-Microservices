package com.example.order_service.Exception;

public class OrderDetailsNotFoundException extends RuntimeException {

    public OrderDetailsNotFoundException(String m)
    {
        super(m) ;
    }

}
