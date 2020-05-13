package com.samuel.springboot.dao;

import com.samuel.springboot.entity.LoginUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    //显示所有用户
    List<LoginUser> getUser() throws Exception;

    List<LoginUser> getUserById(int id) throws Exception;

    //根据id删除用户
    void deleteUser(int id) throws Exception;

    //新增用户
    void addUser(LoginUser user) throws Exception;

}

