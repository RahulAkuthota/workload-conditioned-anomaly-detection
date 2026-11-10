package com.research.userservice.service;

import com.research.userservice.dto.UserRequestDTO;
import com.research.userservice.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO requestDTO);

    UserResponseDTO getUserById(Long userId);

    List<UserResponseDTO> getAllUsers();
}