package com.example.order_service.Controller;

import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.DTO.OrderResponse;
import com.example.order_service.Service.Impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderServiceImpl orderService ;

    @GetMapping("/test")
    public String test() {
        return "Order Service is running";
    }


    @PostMapping("/createOrder")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest)
    {
        OrderResponse orderResponse = orderService.createOrder(orderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @GetMapping("/allOrders")
    public ResponseEntity<List<OrderResponse>> getAllOrders()
    {
     List<OrderResponse> response = orderService.getAllOrders();

     return  ResponseEntity.ok(response);
    }

    @GetMapping("orderById/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id)
    {
        OrderResponse orderResponse = orderService.getOrderById(id);

        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<OrderResponse> updateById(@PathVariable Long id , @RequestBody OrderRequest orderRequest)
    {
        OrderResponse orderResponse = orderService.updateOrderById(id,orderRequest);

        return  ResponseEntity.ok(orderResponse);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id)
    {
        orderService.deleteOrder(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

