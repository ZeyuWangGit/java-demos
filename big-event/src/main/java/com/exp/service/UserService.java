package com.exp.service;

import com.exp.pojo.User;

public interface UserService {
    User findByUsername(String username);

    void register(String username, String password);
}
