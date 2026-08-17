package com.example.user_service.Mapper;

import com.example.user_service.DTO.UserRequest;
import com.example.user_service.DTO.UserResponse;
import com.example.user_service.Entity.User;

public class UserMapper {

    public static User toEntity(UserRequest userRequest)
    {
        User user = new User() ;

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setStatus(userRequest.getStatus());


        return   user ;
    }

    public static UserResponse toResponse(User user )
    {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setStatus(user.getStatus());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());



        return  userResponse ;
    }

}
