package com.UserMnagementProject.UserManagementProject;

import com.UserMnagementProject.UserManagementProject.DTO.UserDTO;
import com.UserMnagementProject.UserManagementProject.controller.UserController;
import com.UserMnagementProject.UserManagementProject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        UserDTO inputDTO = new UserDTO();
        inputDTO.setName("testUser");
        inputDTO.setEmail("test@example.com");

        UserDTO expectedDTO = new UserDTO();
        expectedDTO.setName("testUser");
        expectedDTO.setEmail("test@example.com");

        when(userService.registerUser(inputDTO)).thenReturn(expectedDTO);

        // Act
        ResponseEntity<UserDTO> response = userController.registerUser(inputDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDTO, response.getBody());
    }

    @Test
    public void testGetUserById_UserFound() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UserDTO expectedDTO = new UserDTO();
        expectedDTO.setId(userId);
        expectedDTO.setName("testUser");

        when(userService.getUserById(userId)).thenReturn(Optional.of(expectedDTO));

        // Act
        ResponseEntity<UserDTO> response = userController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDTO, response.getBody());
    }

    @Test
    public void testGetUserById_UserNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();

        when(userService.getUserById(userId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<UserDTO> response = userController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
