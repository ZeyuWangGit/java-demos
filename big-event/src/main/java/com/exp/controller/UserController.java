package com.exp.controller;

import com.exp.dto.UserRegisterDTO;
import com.exp.pojo.Result;
import com.exp.pojo.User;
import com.exp.service.UserService;
import com.exp.utils.Md5Util;
import jakarta.validation.Valid;
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
    public Result register(@RequestBody @Valid UserRegisterDTO dto) {
        User user = userService.findByUsername(dto.getUsername());
        if (user != null) {
            return Result.error("User already exists");
        } else {
            userService.register(dto.getUsername(), dto.getPassword());
            return Result.success();
        }
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid UserRegisterDTO dto) {
        User user = userService.findByUsername(dto.getUsername());
        if (user == null) {
            return Result.error("User does not exist");
        } else if (!user.getPassword().equals(Md5Util.getMD5String(dto.getPassword()))) {
            return Result.error("Incorrect password");
        } else {
            return Result.success("Login successful");
        }
    }
}
