package com.example.order_service.Client;


import com.example.order_service.Config.FeignErrorConfigurer;
import com.example.order_service.DTO.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE" , configuration = FeignErrorConfigurer.class)
public interface UserClient {

    @GetMapping("/users/userById/{id}")
    public UserResponse getUserById(@PathVariable Long id) ;

}
