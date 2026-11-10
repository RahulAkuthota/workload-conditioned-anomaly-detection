package com.research.userservice.service.impl;

import com.research.userservice.dao.UserDao;
import com.research.userservice.dto.UserRequestDTO;
import com.research.userservice.dto.UserResponseDTO;
import com.research.userservice.entity.User;
import com.research.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {

        User user = new User();

        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setCreatedAt(LocalDateTime.now());

        userDao.createUser(user);

        return convertToDTO(user);
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {

        User user = userDao.getUserById(userId);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return convertToDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {

        return userDao.getAllUsers()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    private UserResponseDTO convertToDTO(User user) {

        return new UserResponseDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}