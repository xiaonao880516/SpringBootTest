package com.samuel.springboot.web.controller;

import com.samuel.springboot.biz.UserService;
import com.samuel.springboot.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {


    private UserService userService;
    private LoginUser user;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUser(LoginUser user) {
        this.user = user;
    }

    //显示用户
    @RequestMapping("getUser")
    public List<LoginUser> getUser() throws Exception {
        return userService.getUser();
    }

    //删除用户
    @RequestMapping("delete/{id}")
    public String deleteUser(@PathVariable int id) throws Exception {
        userService.deleteUser(id);
        return "你已经删掉了id为"+id+"的用户";
    }

    //增加用户
    @RequestMapping("addUser")
    public String addUser() throws Exception {
        user.setId(999);
        user.setUsername("mengwei123");
        user.setPassword("123456");
        user.setReal_name("孟伟");
        userService.addUser(user);
        return "增加用户";
    }
}