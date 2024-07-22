package com.example.hackathon.Service;

import com.example.hackathon.Entity.MyUser;
import com.example.hackathon.Repository.MyUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserService implements UserDetailsService{


    MyUserRepository myUserRepository;
    public MyUserService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }



    public List<MyUser> getAllUsers(){
        return myUserRepository.findAll();
    }

    public MyUser getUserByID(Long userId) {
        return myUserRepository.findById(userId).orElse(null);

    }

    public void deleteUserByID(Long userId){

        myUserRepository.deleteById(userId);
    }

    public void createUser(MyUser newMyUser){

        myUserRepository.save(newMyUser);
    }
    public void updateUser(MyUser updatedMyUser, Long userId){

        MyUser myUser = myUserRepository.findById(userId).orElse(null);

        if (myUser !=null) {
            myUser.setRole(updatedMyUser.getRole());
            myUser.setUsername(updatedMyUser.getUsername());
            myUser.setPassword(updatedMyUser.getPassword());
            myUserRepository.save(myUser);
        }
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = myUserRepository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(MyUser user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }
}
