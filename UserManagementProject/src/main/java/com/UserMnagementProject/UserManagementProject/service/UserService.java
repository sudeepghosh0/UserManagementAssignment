package com.UserMnagementProject.UserManagementProject.service;

import com.UserMnagementProject.UserManagementProject.DTO.UserDTO;
import com.UserMnagementProject.UserManagementProject.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public interface UserService {
    public UserDTO registerUser(UserDTO userDTO);
    public Optional<UserDTO> getUserById(UUID id);
}
