package com.example.hznubooks.mapper;

import com.example.hznubooks.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
   List<User> getUsers();
   User getInfoByUsername(String username);

   Integer updateUser(String username, String password, String name, String phone, int type, int number);

   Integer addUser(String username, String password, String name, String phone, int type, int number);

   List<User> getUsersByType(int type);

   Integer delUser(int userid);
}