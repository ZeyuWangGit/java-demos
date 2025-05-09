package com.exp.controller;

import com.exp.dto.UserRegisterDTO;
import com.exp.pojo.Result;
import com.exp.pojo.User;
import com.exp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDTO dto) {
        User user = userService.findByUsername(dto.getUsername());
        if (user != null) {
            return Result.error("User already exists");
        } else {
            userService.register(dto.getUsername(), dto.getPassword());
            return Result.success();
        }

    }
}
