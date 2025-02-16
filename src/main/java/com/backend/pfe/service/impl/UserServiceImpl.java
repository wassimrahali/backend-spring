package com.backend.pfe.service.impl;

import com.backend.pfe.entites.Role;
import com.backend.pfe.entites.User;
import com.backend.pfe.repository.UserRepository;
import com.backend.pfe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;




@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
            }
        };
    }

    public String createPasswordResetToken(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepository.save(user); // Ensure the user is saved with the new token
        return token;
    }

    public boolean validatePasswordResetToken(String token) {
        return userRepository.findByResetToken(token).isPresent();
    }

    public boolean resetPassword(String token, String newPassword) {
        var user = userRepository.findByResetToken(token).orElse(null);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetToken(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }



    @Override
    public User addCollaborator(User collaborator) {
        collaborator.setPassword(passwordEncoder.encode(collaborator.getPassword()));
        collaborator.setRole(Role.COLLABORATOR); // Set the role to COLLABORATOR
        return userRepository.save(collaborator);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllByTeamId(Integer teamId) {
        return userRepository.findAllByTeamId(teamId);
    }
}