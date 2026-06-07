package com.rulex.rulex.Service;


import com.rulex.rulex.Entity.User;
import com.rulex.rulex.Exception.CustomException.UserNotFound;
import com.rulex.rulex.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User findByUserName(String name){
        User user =  userRepo.findByUserName(name);
        if(user==null) throw new UserNotFound("User not found");
        return user;
    }

    public User updateUser(String userName,User user1){
        User user = findByUserName(userName);
        user.setUserName(user1.getUserName());
        user.setPassword(passwordEncoder.encode(user1.getPassword()));
        user.setRole(user1.getRole());
        user.setEmail(user1.getEmail());
        return userRepo.save(user);
    }

    public void deleteUserByUserName(String userName){
        User user = findByUserName(userName);
        userRepo.delete(user);
    }
}
