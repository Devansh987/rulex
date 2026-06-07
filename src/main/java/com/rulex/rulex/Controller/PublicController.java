package com.rulex.rulex.Controller;


import com.rulex.rulex.Entity.User;
import com.rulex.rulex.Service.EmailService;
import com.rulex.rulex.Service.UserDetailServiceImpl;
import com.rulex.rulex.Service.UserService;
import com.rulex.rulex.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private EmailService  emailService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
//        emailService.SendEmail(user.getEmail(),"Account Created Successfully","Your Account has been Created on rules You can use the application after login");
        return new ResponseEntity<>(userService.saveNewUser(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserName());
            String role = userDetails.getAuthorities().iterator().next().getAuthority();
            String jwt = jwtUtil.generateToken(userDetails.getUsername(), role);

            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
//            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }


}
