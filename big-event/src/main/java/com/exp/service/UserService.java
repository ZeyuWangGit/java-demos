package com.exp.service;

import com.exp.pojo.User;

public interface UserService {
    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePassword(String newPassword);
}
