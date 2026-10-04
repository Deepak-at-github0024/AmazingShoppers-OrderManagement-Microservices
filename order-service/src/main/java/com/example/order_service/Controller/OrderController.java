package com.example.order_service.Controller;

import com.example.order_service.Client.ProductClient;
import com.example.order_service.Client.UserClient;
import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.DTO.OrderResponse;
import com.example.order_service.DTO.ProductResponse;
import com.example.order_service.DTO.UserResponse;
import com.example.order_service.Service.Impl.OrderServiceImpl;
import com.example.order_service.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserClient userClient;

    @Autowired
   // private OrderServiceImpl orderService;
    private OrderService orderService ;

    @GetMapping("/test-product/{id}")
    public ResponseEntity<ProductResponse> getProductFromProductService(@PathVariable Long id) {
        ProductResponse productResponse = productClient.getProductById(id);

        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/userVerification/{id}")
    public ResponseEntity<UserResponse> verifyUserExistsOrNo(@PathVariable Long id) {
        UserResponse userResponse = userClient.getUserById(id);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/test")
    public String test() {
        return "Order Service is running";
    }


    @PostMapping("/createOrder")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {


        OrderResponse orderResponse = orderService.createOrder(orderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @GetMapping("/allOrders")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> response = orderService.getAllOrders();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/orderById/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        OrderResponse orderResponse = orderService.getOrderById(id);

        return ResponseEntity.ok(orderResponse);
    }

    @PatchMapping("/updateById/{id}")
    public ResponseEntity<OrderResponse> updateById(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.updateOrderById(id, orderRequest);

        return ResponseEntity.ok(orderResponse);
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<?> deleteById(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/deleteByUserId/{userId}")
    public ResponseEntity<?> deleteByUserId(@PathVariable Long userId)
    {
        orderService.deletOrderByUserId(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/deleteByProductId/{productId}")
    public ResponseEntity<?> deleteByProductId(@PathVariable Long productId)
    {
        orderService.deleteOrderByProductId(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



    @GetMapping("/ordersByUserId/{id}")
    public ResponseEntity<List<OrderResponse>> getOrderListByUserId(@PathVariable Long id)
    {
       List<OrderResponse> orderResponse = orderService.getOrderListByUserId(id);

       return ResponseEntity.ok(orderResponse);
    }

}

