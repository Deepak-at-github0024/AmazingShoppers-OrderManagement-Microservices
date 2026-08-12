package com.example.order_service.Mapper;

import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.DTO.OrderResponse;
import com.example.order_service.Entity.Order;

public class OrderMapper {


    public static Order toEntity(OrderRequest request)
    {
        Order order = new Order();

        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(request.getTotalAmount());
        order.setStatus(request.getStatus());



        return order ;
    }

    public static OrderResponse toResponse(Order order)
    {
        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setProductId(order.getProductId());
        orderResponse.setQuantity(order.getQuantity());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());

        return orderResponse ;

    }
}
