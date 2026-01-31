package com.rulex.rulex.Service;

import com.rulex.rulex.Entity.User;
import com.rulex.rulex.Repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailServiceImpl(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("User does not Exists");
        }

        return org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
                .password(user.getPassword()).authorities("ROLE_" + user.getRole().name()).build();
    }
}
