package com.UserMnagementProject.UserManagementProject.customexception;

public class ValidationFailureException extends RuntimeException {
    public ValidationFailureException(String message) {
        super(message);
    }
}
