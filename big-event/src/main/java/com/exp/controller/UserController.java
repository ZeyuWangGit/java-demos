package com.exp.controller;

import com.exp.context.TokenContext;
import com.exp.dto.UserRegisterDTO;
import com.exp.pojo.Result;
import com.exp.pojo.User;
import com.exp.service.UserService;
import com.exp.utils.JwtUtil;
import com.exp.utils.Md5Util;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", user.getUsername());
            claims.put("userId", user.getId());
            String token = JwtUtil.generateToken(claims);
            return Result.success(token);
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> claims = TokenContext.get();

        String name = (String) claims.get("username");
        User user = userService.findByUsername(name);

        return Result.success(user);
    }

    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody User user) {
        // Assuming you have a method in your service to update user info
        userService.update(user);
        return Result.success("User info updated successfully");
    }
}
