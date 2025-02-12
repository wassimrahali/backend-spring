package com.backend.pfe.service.impl;

import com.backend.pfe.dto.SignUpRequest;
import com.backend.pfe.entites.Role;
import com.backend.pfe.entites.User;
import com.backend.pfe.repository.UserRepository;
import com.backend.pfe.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;
    private  PasswordEncoder passwordEncoder;

    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();

        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }
}
