package com.rulex.rulex.Controller;

import com.rulex.rulex.Entity.Role;
import com.rulex.rulex.Entity.User;
import com.rulex.rulex.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveNewUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        userService.deleteUserByUserName(username);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}


