package com.UserMnagementProject.UserManagementProject.mapper;

import com.UserMnagementProject.UserManagementProject.DTO.UserDTO;
import com.UserMnagementProject.UserManagementProject.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .age(user.getAge())
                .username(user.getUsername())
                .country(user.getCountry())
                .createdDate(user.getCreatedDate())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }

        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .age(userDTO.getAge())
                .username(userDTO.getUsername())
                .country(userDTO.getCountry())
                .createdDate(userDTO.getCreatedDate())
                .build();
    }

}
