package com.example.hznubooks.service;

import com.example.hznubooks.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getInfoByUsername(String username);
    boolean login(String username, String password);

    int updateUser(String username, String password, String name, String phone, int type, int number);

    int addUser(String username, String password, String name, String phone, int type, int number);

    List<User> getUsersByType(int type);

    int delUser(int userid);
}
