package com.example.user_service.Service.Impl;

import com.example.user_service.DTO.UserRequest;
import com.example.user_service.DTO.UserResponse;
import com.example.user_service.Entity.User;
import com.example.user_service.Mapper.UserMapper;
import com.example.user_service.Repository.UserRepository;
import com.example.user_service.Service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        List<UserResponse> userResponseList = new ArrayList<>();

        List<User> userList = userRepository.findAll();
        if(!CollectionUtils.isEmpty(userList))
        {
           for(User user : userList)
           {
               UserResponse userResponse =  UserMapper.toResponse(user);
               userResponseList.add(userResponse);
           }
        }
        return  userResponseList ;
    }

    @Override
    public UserResponse getUserById(Long id) {

        UserResponse userResponse = new UserResponse();

        Optional<User> user = userRepository.findById(id) ;
        if(user.isPresent())
        {
            userResponse = UserMapper.toResponse(user.get()) ;
        }
        return userResponse ;
    }

    @Override
    public UserResponse updateUserbyId(Long id , UserRequest us) {

        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
