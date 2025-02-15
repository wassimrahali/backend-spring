package com.backend.pfe.service;

import com.backend.pfe.dto.*;
import com.backend.pfe.entites.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthentificationResponse signIn(SignInRequest signInRequest);
    JwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    User createManager(ManagerRegistrationRequest request);

}
