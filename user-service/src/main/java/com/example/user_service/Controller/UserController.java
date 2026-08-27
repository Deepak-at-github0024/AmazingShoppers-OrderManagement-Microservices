package com.example.user_service.Controller;

import com.example.user_service.DTO.UserRequest;
import com.example.user_service.DTO.UserResponse;
import com.example.user_service.Service.Impl.UserServiceImpl;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id)
    {
        UserResponse userResponse = userService.getUserById(id);

        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest us)
    {
        UserResponse userResponse = userService.updateUserbyId(id, us);

        return  ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}