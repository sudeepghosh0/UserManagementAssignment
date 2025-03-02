package com.UserMnagementProject.UserManagementProject.serviceimpl;

import com.UserMnagementProject.UserManagementProject.DTO.UserDTO;
import com.UserMnagementProject.UserManagementProject.customexception.DuplicateUserException;
import com.UserMnagementProject.UserManagementProject.customexception.ValidationFailureException;
import com.UserMnagementProject.UserManagementProject.entity.User;
import com.UserMnagementProject.UserManagementProject.mapper.UserMapper;
import com.UserMnagementProject.UserManagementProject.repository.UserRepository;
import com.UserMnagementProject.UserManagementProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        if (userDTO.getAge() < 18 || !"France".equalsIgnoreCase(userDTO.getCountry())) {
            throw new ValidationFailureException("Only adults who reside in France can create an account");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateUserException("A user with this email already exists");
        }
        User user = UserMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }



    @Override
    public Optional<UserDTO> getUserById(UUID id) {
        return userRepository.findById(id).map(UserMapper::toDTO);
    }
}
