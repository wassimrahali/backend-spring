package com.backend.pfe.dto;

public class PasswordResetConfirmRequest {
    private String newPassword;

    // Getters and setters
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}