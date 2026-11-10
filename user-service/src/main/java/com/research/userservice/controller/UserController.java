package com.research.userservice.controller;

import com.research.userservice.dto.UserRequestDTO;
import com.research.userservice.dto.UserResponseDTO;
import com.research.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserResponseDTO createUser(
            @RequestBody UserRequestDTO requestDTO) {

        return userService.createUser(requestDTO);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(
            @PathVariable Long id) {

        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {

        return userService.getAllUsers();
    }
}