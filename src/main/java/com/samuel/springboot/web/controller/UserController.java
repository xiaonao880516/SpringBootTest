package com.samuel.springboot.web.controller;

import com.alibaba.fastjson.JSON;
import com.samuel.springboot.biz.UserService;
import com.samuel.springboot.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

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
    @RequestMapping("getJsonObject/{id}")
    public String getJsonObject(@PathVariable int id) throws Exception {
//        return JSON.toJSONString(userService.getUser(id));
//        return userService.getUser(id).get(0);
        return "{\n" +
                "\"id\": 38,\n" +
                "\"password\": \"d210b7c1d167ec8ec682ace4ee85399b\",\n" +
                "\"real_name\": \"管理员\",\n" +
                "\"username\": \"admin\"\n" +
                "}";
    }

    //显示用户
    @RequestMapping("getUser")
    public List<LoginUser> getUser() throws Exception {
        return userService.getUser();
    }

    //显示用户
    @RequestMapping("getUser/{id}")
    public List<LoginUser> getUser(@PathVariable int id) throws Exception {
        return userService.getUser(id);
    }

    //删除用户
    @RequestMapping("delete/{id}")
    public String deleteUser(@PathVariable int id) throws Exception {
        userService.deleteUser(id);
        return "你已经删掉了id为" + id + "的用户";
    }

    //增加用户
    @RequestMapping(value = "addUserGet", method = RequestMethod.GET)
    public String addUserGet(@RequestParam("id") Integer id, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("real_name") String realName) throws Exception {
        return addUser(id, username, password, realName);
    }

    //增加用户
    @RequestMapping(value = "addUserPost", method = RequestMethod.POST)
    public String addUserPost(@RequestParam("id") Integer id, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("real_name") String realName) throws Exception {
        return addUser(id, username, password, realName);
    }

    private String addUser(Integer id, String username, String password, String realName) throws Exception {
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setReal_name(realName);
        userService.addUser(user);
        return "增加用户" + user.toString();
    }
}