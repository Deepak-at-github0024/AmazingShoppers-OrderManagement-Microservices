package com.example.user_service.Service;

import com.example.user_service.DTO.UserRequest;
import com.example.user_service.DTO.UserResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest) ;

    List<UserResponse> getAllUsers() ;

    UserResponse getUserById (Long id) ;

    UserResponse updateUserbyId (Long id , UserRequest userRequest) ;

    void deleteUser(Long id) ;


}
