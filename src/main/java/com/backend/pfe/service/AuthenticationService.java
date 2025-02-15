package com.backend.pfe.service;

import com.backend.pfe.dto.*;
import com.backend.pfe.entites.User;

import java.util.List;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthentificationResponse signIn(SignInRequest signInRequest);
    JwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    User createManager(ManagerRegistrationRequest request);
    List<User> getAllManagers();
    User updateManager(Integer id, ManagerRegistrationRequest request);
    void deleteManager(Integer id);
    User getManagerById(Integer id);}
