package com.exp.controller;

import com.exp.context.TokenContext;
import com.exp.dto.UpdatePasswordDTO;
import com.exp.dto.UserRegisterDTO;
import com.exp.dto.UserUpdateDTO;
import com.exp.mapper.UserConvert;
import com.exp.pojo.Result;
import com.exp.pojo.User;
import com.exp.service.UserService;
import com.exp.utils.JwtUtil;
import com.exp.utils.Md5Util;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConvert userConvert;

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
    public Result<Object> login(@RequestBody @Valid UserRegisterDTO dto) {
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
    public Result updateUserInfo(@RequestBody @Validated UserUpdateDTO userDto) {
        // Assuming you have a method in your service to update user info
        User user = userConvert.toEntity(userDto);
        userService.update(user);
        return Result.success("User info updated successfully");
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePassword")
    public Result updatePassword(@RequestBody @Validated UpdatePasswordDTO updatePasswordDTO) {

        Map<String, Object> claims = TokenContext.get();
        String name = (String) claims.get("username");
        User user = userService.findByUsername(name);
        if (user == null) {
            return Result.error("User does not exist");
        } else if (!user.getPassword().equals(Md5Util.getMD5String(updatePasswordDTO.getOldPassword()))) {
            return Result.error("Incorrect old password");
        } else if (!updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getRePassword())){
            return Result.error("New password and confirmation do not match");
        } else if (updatePasswordDTO.getNewPassword().equals(updatePasswordDTO.getOldPassword())) {
            return Result.error("New password cannot be the same as old password");
        } else {
            user.setPassword(Md5Util.getMD5String(updatePasswordDTO.getNewPassword()));
            userService.updatePassword(updatePasswordDTO.getNewPassword());
            return Result.success("Password updated successfully");
        }
    }
}
