package com.rulex.rulex.Service;


import com.rulex.rulex.Entity.Role;
import com.rulex.rulex.Entity.User;
import com.rulex.rulex.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User saveNewUser(User user){
        return userRepo.save(user);
    }


    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User findByUserName(String name){
        return userRepo.findByUserName(name);
    }

    public void deleteUserByUserName(String userName){
        User user = userRepo.findByUserName(userName);
        userRepo.delete(user);
    }
}
