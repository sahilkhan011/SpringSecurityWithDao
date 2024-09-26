package com.ssk.SpringSecurityWithDAO.rest;

import com.ssk.SpringSecurityWithDAO.model.User;
import com.ssk.SpringSecurityWithDAO.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("home")
    public String greet(){
        return "Hello Welcome to App";
    }

    @PostMapping("register")
    public User register(@RequestBody User user){
        return userService.createUser(user);
    }
}
