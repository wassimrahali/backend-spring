package com.backend.pfe.service;

import com.backend.pfe.dto.JwtAuthentificationResponse;
import com.backend.pfe.dto.RefreshTokenRequest;
import com.backend.pfe.dto.SignInRequest;
import com.backend.pfe.dto.SignUpRequest;
import com.backend.pfe.entites.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthentificationResponse signIn(SignInRequest signInRequest);
    JwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
