package com.example.order_service.Client;


import com.example.order_service.Config.FeignErrorConfigurer;
import com.example.order_service.DTO.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE" , configuration = FeignErrorConfigurer.class)
public interface ProductClient {

    @GetMapping("/products/byId/{id}")
    ProductResponse getProductById(@PathVariable("id") Long id);
}

