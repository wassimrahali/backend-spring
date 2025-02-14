package com.backend.pfe.dto;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String email;
}