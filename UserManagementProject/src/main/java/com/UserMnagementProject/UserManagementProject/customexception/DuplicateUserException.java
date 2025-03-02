package com.UserMnagementProject.UserManagementProject.customexception;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message) {
        super(message);
    }
}
