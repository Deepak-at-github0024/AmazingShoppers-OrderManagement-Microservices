package com.example.user_service.Controller;

import com.example.user_service.DTO.UserRequest;
import com.example.user_service.DTO.UserResponse;
import com.example.user_service.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService ;

    @GetMapping("/test")
    public String test() {
        return "User Service is running";
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest)
    {
        UserResponse userResponse = userService.createUser(userRequest) ;

        return  ResponseEntity.ok(userResponse);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserResponse>> getAllUsers()
    {
        List<UserResponse> allUsers = userService.getAllUsers();

        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) throws UserPrincipalNotFoundException {
        UserResponse userResponse = userService.getUserById(id);

        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest us) throws UserPrincipalNotFoundException {
        UserResponse userResponse = userService.updateUserById(id, us);

        return  ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}