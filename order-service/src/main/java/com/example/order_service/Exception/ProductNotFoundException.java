package com.example.order_service.Exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String m)
    {
        super(m);
    }
}