package com.lingfenglong.rpc.server.service.impl;

import com.lingfenglong.rpc.entity.User;
import com.lingfenglong.rpc.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User findById(Integer id) {
        return new User(id, "LingFenglong", 21);
    }
}
