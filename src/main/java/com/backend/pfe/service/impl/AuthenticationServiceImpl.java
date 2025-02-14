package com.backend.pfe.service.impl;

import com.backend.pfe.dto.JwtAuthentificationResponse;
import com.backend.pfe.dto.RefreshTokenRequest;
import com.backend.pfe.dto.SignInRequest;
import com.backend.pfe.dto.SignUpRequest;
import com.backend.pfe.entites.Role;
import com.backend.pfe.entites.User;
import com.backend.pfe.repository.UserRepository;
import com.backend.pfe.service.AuthenticationService;
import com.backend.pfe.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.COLLABORATOR);
        return userRepository.save(user);
    }

    @Override
    public JwtAuthentificationResponse signIn(SignInRequest signInRequest) {
        var user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
            );
        } catch (Exception e) {
            throw new RuntimeException("Invalid email or password");
        }

        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthentificationResponse response = new JwtAuthentificationResponse();
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);
        return response;
    }


    public JwtAuthentificationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);
            JwtAuthentificationResponse response = new JwtAuthentificationResponse();
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            return response;
        }
        return null;
    }

}