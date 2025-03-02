package com.UserMnagementProject.UserManagementProject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "User_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false,nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(name = "emailId", unique = true)
    private String email;

    @Column(name="age")
    private int age;

    @NotBlank(message = "Username is mandatory")
    @Column(name="username", unique = true)
    private String username;

    @Column(name="country")
    private String country;

    @Column(name="created_date")
    private LocalDateTime createdDate;
}
