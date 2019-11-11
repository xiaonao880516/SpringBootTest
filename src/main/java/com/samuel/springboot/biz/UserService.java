package com.samuel.springboot.biz;

import com.samuel.springboot.entity.LoginUser;

import java.util.List;

public interface UserService {

        //显示所有用户
        public List<LoginUser> getUser() throws Exception;
        //根据id删除用户
        public void deleteUser(int id) throws Exception;
        //新增用户
        public void addUser(LoginUser user) throws Exception;

}
