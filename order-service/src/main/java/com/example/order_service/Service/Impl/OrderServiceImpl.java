package com.example.order_service.Service.Impl;

import com.example.order_service.Client.ProductClient;
import com.example.order_service.Client.UserClient;
import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.DTO.OrderResponse;
import com.example.order_service.DTO.ProductResponse;
import com.example.order_service.DTO.UserResponse;
import com.example.order_service.Entity.Order;
import com.example.order_service.Exception.OrderDetailsNotFoundException;
import com.example.order_service.Mapper.OrderMapper;
import com.example.order_service.Repository.OrderRepository;
import com.example.order_service.Service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository ;
    private final ProductClient productClient ;
    private final UserClient userClient ;

    public OrderServiceImpl(OrderRepository orderRepository, ProductClient productClient, UserClient userClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        ProductResponse product = productClient.getProductById(orderRequest.getProductId());
        UserResponse user = userClient.getUserById(orderRequest.getUserId());

        Order order = OrderMapper.toEntity(orderRequest);
        BigDecimal totalOrderAmount = product.getPrice().multiply(BigDecimal.valueOf(orderRequest.getQuantity()));
        order.setTotalAmount(totalOrderAmount);
        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {

        List<Order>allOrders = orderRepository.findAll();

        return  allOrders.stream().map(OrderMapper::toResponse).toList();
    }

    @Override
    public OrderResponse getOrderById(@PathVariable  Long id) {

        Optional<Order> orderById = orderRepository.findById(id) ;

        return orderById.stream().map(OrderMapper::toResponse).findAny().orElseThrow(()-> new OrderDetailsNotFoundException("No Order Exists for the id "+id));

    }

    @Override
    public List<OrderResponse> getOrderListByUserId(Long id) {

        List<Order> userOrderList = orderRepository.findByUserId(id);

        if(CollectionUtils.isEmpty(userOrderList))
        {
            throw new OrderDetailsNotFoundException("No Order exist for the user");
        }

        return userOrderList.stream().map(OrderMapper::toResponse).toList();
    }

    @Override
    public OrderResponse updateOrderById(@PathVariable  Long id,@RequestBody OrderRequest orderRequest) {

        Optional<Order> updateOrder = orderRepository.findById(id);
        if(updateOrder.isEmpty())
        {
            throw new OrderDetailsNotFoundException("No Order exist for id "+id) ;
        }


            Order order = updateOrder.get();

            order.setUserId(orderRequest.getUserId());
            order.setProductId(orderRequest.getProductId());
            order.setQuantity(orderRequest.getQuantity());

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
            throw new OrderDetailsNotFoundException("No Order exist for id "+id) ;
        }

    }
}
