package com.example.hznubooks.service.impl;

import com.example.hznubooks.entity.User;
import com.example.hznubooks.mapper.UserMapper;
import com.example.hznubooks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }
    public User getInfoByUsername(String username) {
        return userMapper.getInfoByUsername(username);
    }
    @Override
    public boolean login(String username, String password) {
        return Objects.equals((userMapper.getInfoByUsername(username)).getPassword(),password);
    }

    @Override
    public int updateUser(String username, String password, String name, String phone, int type, int number) {
        userMapper.updateUser(username, password, name, phone, type, number);
        return 1;
    }

    @Override
    public int addUser(String username, String password, String name, String phone, int type, int number) {
        userMapper.addUser(username, password, name, phone, type, number);
        return 1;
    }

    @Override
    public List<User> getUsersByType(int type) {
        return userMapper.getUsersByType(type);
    }

    @Override
    public int delUser(int userid) {
        userMapper.delUser(userid);
        return 1;
    }


}
