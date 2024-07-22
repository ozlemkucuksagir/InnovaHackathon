package com.example.hackathon.Controller;

import com.example.hackathon.Entity.MyUser;
import com.example.hackathon.Service.MyUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")

public class MyUserController {

    MyUserService myUserService;

    public MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

   @GetMapping("/getAllUsers")
    List<MyUser> getAllUsers (){
       List<MyUser> myUsers = myUserService.getAllUsers();
       return ResponseEntity.ok(myUsers).getBody();
    }

    @GetMapping("/getUserByID/{userId}")
    MyUser getUserByID (@PathVariable Long userId){
        return myUserService.getUserByID(userId);
    }

    @PutMapping("/updateUserByID/{userId}")
    public void updateUser(@RequestBody MyUser updated_My_user, @PathVariable Long userId){
        myUserService.updateUser(updated_My_user,userId);
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody MyUser newMyUser){
        myUserService.createUser(newMyUser);
    }

    @DeleteMapping("/deleteUserByID/{userId}")
    public void deleteUser(@PathVariable Long userId){
        myUserService.deleteUserByID(userId);

    }
}
