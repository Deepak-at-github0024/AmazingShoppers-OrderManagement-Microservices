package com.example.user_service.Service;

import com.example.user_service.DTO.UserRequest;
import com.example.user_service.DTO.UserResponse;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest) ;

    List<UserResponse> getAllUsers() ;

    UserResponse getUserById (Long id)  ;

    UserResponse updateUserById (Long id , UserRequest userRequest) throws UserPrincipalNotFoundException;

    void deleteUser(Long id) ;


}
