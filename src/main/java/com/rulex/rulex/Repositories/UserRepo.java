package com.rulex.rulex.Repositories;

import com.rulex.rulex.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByUserName(String name);

//    List<User> findAllUser();
}
