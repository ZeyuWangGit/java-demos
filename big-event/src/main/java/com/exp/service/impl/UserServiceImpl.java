package com.exp.service.impl;

import com.exp.context.TokenContext;
import com.exp.mapper.UserMapper;
import com.exp.pojo.User;
import com.exp.service.UserService;
import com.exp.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username, md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> claims = TokenContext.get();
        Integer id = (Integer) claims.get("userId");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePassword(String newPassword) {
        Map<String, Object> claims = TokenContext.get();
        Integer id = (Integer) claims.get("userId");
        userMapper.updatePassword(Md5Util.getMD5String(newPassword), id);
    }
}
