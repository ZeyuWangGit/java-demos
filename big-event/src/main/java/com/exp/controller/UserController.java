package com.exp.controller;

import com.exp.pojo.Result;
import com.exp.pojo.User;
import com.exp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return Result.error("User already exists");
        } else {
            userService.register(username, password);
            return Result.success();
        }

    }
}
