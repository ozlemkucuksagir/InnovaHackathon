package com.example.hackathon.Controller;

import com.example.hackathon.Entity.User;
import com.example.hackathon.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

   @GetMapping("/getAllUsers")
    List<User> getAllUsers (){
       List<User> users = userService.getAllUsers();
       return ResponseEntity.ok(users).getBody();
    }

    @GetMapping("/getUserByID/{userId}")
    User getUserByID (@PathVariable Long userId){
        return userService.getUserByID(userId);
    }

    @PutMapping("/updateUserByID/{userId}")
    public void updateUser(@RequestBody User updated_user,@PathVariable Long userId){
        userService.updateUser(updated_user,userId);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User newUser){
        userService.createUser(newUser);
    }

    @DeleteMapping("/deleteUserByID/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUserByID(userId);

    }
}
