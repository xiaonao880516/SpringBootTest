package com.samuel.springboot.biz.impl;

import com.samuel.springboot.biz.UserService;
import com.samuel.springboot.dao.UserMapper;
import com.samuel.springboot.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<LoginUser> getUser() throws Exception {
        return userMapper.getUser();
    }

    @Override
    public void deleteUser(int id) throws Exception {
        userMapper.deleteUser(id);
    }

    @Override
    public void addUser(LoginUser user) throws Exception {
        userMapper.addUser(user);
    }
}
