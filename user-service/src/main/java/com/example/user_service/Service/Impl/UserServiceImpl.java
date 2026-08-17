package com.example.user_service.Service.Impl;

import com.example.user_service.DTO.UserRequest;
import com.example.user_service.DTO.UserResponse;
import com.example.user_service.Entity.User;
import com.example.user_service.Mapper.UserMapper;
import com.example.user_service.Repository.UserRepository;
import com.example.user_service.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository ;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserResponse createUser(UserRequest userRequest) {

        User user = UserMapper.toEntity(userRequest);

        User savedUser =  userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public UserResponse getUserById(Long id) {
        return null;
    }

    @Override
    public UserResponse updateUserbyId(Long id , UserRequest us) {

        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
