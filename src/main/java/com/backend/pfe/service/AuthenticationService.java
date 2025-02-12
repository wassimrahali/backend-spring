package com.backend.pfe.service;

import com.backend.pfe.dto.SignUpRequest;
import com.backend.pfe.entites.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
}
