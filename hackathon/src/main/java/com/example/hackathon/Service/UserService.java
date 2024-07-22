package com.example.hackathon.Service;

import com.example.hackathon.Entity.User;
import com.example.hackathon.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByID(Long userId) {
        return userRepository.findById(userId).orElse(null);

    }

    public void deleteUserByID(Long userId){

        userRepository.deleteById(userId);
    }

    public void createUser(User newUser){

        userRepository.save(newUser);
    }
    public void updateUser(User updatedUser, Long userId){

        User user=userRepository.findById(userId).orElse(null);

        if (user!=null) {
            user.setRole(updatedUser.getRole());
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            userRepository.save(user);
        }
    }



}
