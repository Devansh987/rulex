package com.rulex.rulex.Service;


import com.rulex.rulex.Entity.Role;
import com.rulex.rulex.Entity.User;
import com.rulex.rulex.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


    public User saveNewUser(User user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return userRepo.save(user);
    }


    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User findByUserName(String name){
        return userRepo.findByUserName(name);
    }

    public User updateUser(String userName,User user1){
        User user = userRepo.findByUserName(userName);
        user.setUserName(user1.getUserName());
        user.setPassword(user.getPassword());
        user.setRole(user.getRole());
        return userRepo.save(user);
    }

    public void deleteUserByUserName(String userName){
        User user = userRepo.findByUserName(userName);
        userRepo.delete(user);
    }
}
