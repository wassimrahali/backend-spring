package com.backend.pfe.dto;

import lombok.Data;

@Data
public class ManagerRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}