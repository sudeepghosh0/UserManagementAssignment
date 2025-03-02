package com.UserMnagementProject.UserManagementProject.controller;

import com.UserMnagementProject.UserManagementProject.DTO.UserDTO;
import com.UserMnagementProject.UserManagementProject.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    /**
     * Registers a new user.
     *
     * @param userDTO the user to register
     * @return the registered user or an error message
     */
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(registeredUser);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the UUID of the user
     * @return the user or a 404 status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        Optional<UserDTO> userDTO = userService.getUserById(id);
        return userDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
