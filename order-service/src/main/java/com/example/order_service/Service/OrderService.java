package com.example.order_service.Service;

import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.DTO.OrderResponse;
import com.example.order_service.Entity.Order;

import java.util.List;

public interface OrderService {

   public OrderResponse createOrder(OrderRequest orderRequest) ;

   public List<OrderResponse> getAllOrders();

   public OrderResponse getOrderById(Long id) ;

   public OrderResponse updateOrderById(Long id , OrderRequest orderRequest) ;

   public void deleteOrder(Long id) ;


}
