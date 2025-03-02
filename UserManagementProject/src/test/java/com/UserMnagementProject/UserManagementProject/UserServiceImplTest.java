package com.UserMnagementProject.UserManagementProject;

import com.UserMnagementProject.UserManagementProject.DTO.UserDTO;
import com.UserMnagementProject.UserManagementProject.customexception.DuplicateUserException;
import com.UserMnagementProject.UserManagementProject.customexception.ValidationFailureException;
import com.UserMnagementProject.UserManagementProject.entity.User;
import com.UserMnagementProject.UserManagementProject.mapper.UserMapper;
import com.UserMnagementProject.UserManagementProject.repository.UserRepository;
import com.UserMnagementProject.UserManagementProject.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        // Arrange
        UserDTO inputDTO = new UserDTO();
        inputDTO.setUsername("testUser");
        inputDTO.setEmail("test@example.com");
        inputDTO.setAge(20);
        inputDTO.setCountry("France");

        User userEntity = UserMapper.toEntity(inputDTO);
        userEntity.setId(UUID.randomUUID()); // Simulate saved entity with ID

        when(userRepository.existsByEmail(inputDTO.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(userEntity);

        // Act
        UserDTO resultDTO = userService.registerUser(inputDTO);

        // Assert
        assertNotNull(resultDTO.getId());
        assertEquals(inputDTO.getUsername(), resultDTO.getUsername());
        assertEquals(inputDTO.getEmail(), resultDTO.getEmail());
    }

    @Test
    public void testRegisterUser_ValidationFailure_Age() {
        // Arrange
        UserDTO inputDTO = new UserDTO();
        inputDTO.setUsername("testUser");
        inputDTO.setEmail("test@example.com");
        inputDTO.setAge(17);
        inputDTO.setCountry("France");

        // Act & Assert
        assertThrows(ValidationFailureException.class, () -> userService.registerUser(inputDTO));
    }

    @Test
    public void testRegisterUser_ValidationFailure_Country() {
        // Arrange
        UserDTO inputDTO = new UserDTO();
        inputDTO.setUsername("testUser");
        inputDTO.setEmail("test@example.com");
        inputDTO.setAge(20);
        inputDTO.setCountry("USA");

        // Act & Assert
        assertThrows(ValidationFailureException.class, () -> userService.registerUser(inputDTO));
    }

    @Test
    public void testRegisterUser_DuplicateUser() {
        // Arrange
        UserDTO inputDTO = new UserDTO();
        inputDTO.setUsername("testUser");
        inputDTO.setEmail("test@example.com");
        inputDTO.setAge(20);
        inputDTO.setCountry("France");

        when(userRepository.existsByEmail(inputDTO.getEmail())).thenReturn(true);

        // Act & Assert
        assertThrows(DuplicateUserException.class, () -> userService.registerUser(inputDTO));
    }

    @Test
    public void testGetUserById_UserFound() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User userEntity = new User();
        userEntity.setId(userId);
        userEntity.setUsername("testUser");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

        // Act
        Optional<UserDTO> resultDTO = userService.getUserById(userId);

        // Assert
        assertTrue(resultDTO.isPresent());
        assertEquals(userEntity.getId(), resultDTO.get().getId());
        assertEquals(userEntity.getUsername(), resultDTO.get().getUsername());
    }

    @Test
    public void testGetUserById_UserNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<UserDTO> resultDTO = userService.getUserById(userId);

        // Assert
        assertFalse(resultDTO.isPresent());
    }
}
