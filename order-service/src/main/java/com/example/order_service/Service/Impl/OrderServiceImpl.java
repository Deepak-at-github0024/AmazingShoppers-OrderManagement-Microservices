package com.example.order_service.Service.Impl;

import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.DTO.OrderResponse;
import com.example.order_service.Entity.Order;
import com.example.order_service.Mapper.OrderMapper;
import com.example.order_service.Repository.OrderRepository;
import com.example.order_service.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository ;

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        Order order = OrderMapper.toEntity(orderRequest);
        Order savedOrder = orderRepository.save(order) ;

        return  OrderMapper.toResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {

        List<Order>allOrders = orderRepository.findAll();

        return  allOrders.stream().map(OrderMapper::toResponse).toList();
    }

    @Override
    public OrderResponse getOrderById(@PathVariable  Long id) {

        Optional<Order> orderById = orderRepository.findById(id) ;

        return orderById.stream().map(OrderMapper::toResponse).findAny().orElseThrow(()-> new RuntimeException("No Order Exists for the id"));

    }

    @Override
    public OrderResponse updateOrderById(@PathVariable  Long id,@RequestBody OrderRequest orderRequest) {

        Optional<Order> updateOrder = orderRepository.findById(id);
            Order order = updateOrder.get();

            order.setUserId(orderRequest.getUserId());
            order.setProductId(orderRequest.getProductId());
            order.setQuantity(orderRequest.getQuantity());
            order.setTotalAmount(orderRequest.getTotalAmount());
            order.setStatus(orderRequest.getStatus());

            orderRepository.save(order);

            return new OrderResponse(order.getId(),order.getUserId(),order.getProductId(),order.getQuantity(),
                    order.getTotalAmount(),order.getStatus(),order.getCreatedAt(),order.getUpdatedAt());

    }

    @Override
    public void deleteOrder(Long id) {

        Optional<Order> ordders = orderRepository.findById(id) ;

        if(ordders.isPresent()) {
            orderRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("No Order Exists with Id") ;
        }

    }
}
