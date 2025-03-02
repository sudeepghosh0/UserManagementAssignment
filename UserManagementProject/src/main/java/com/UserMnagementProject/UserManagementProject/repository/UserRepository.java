package com.UserMnagementProject.UserManagementProject.repository;

import com.UserMnagementProject.UserManagementProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {
    boolean existsByEmail(String email);
}
