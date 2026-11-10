package com.research.userservice.dao;

import com.research.userservice.entity.User;
import java.util.List;

public interface UserDao {

    void createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();
}