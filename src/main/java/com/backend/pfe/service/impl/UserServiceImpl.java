package com.backend.pfe.service.impl;
import org.thymeleaf.context.Context;

import com.backend.pfe.entites.Role;
import com.backend.pfe.entites.User;
import com.backend.pfe.repository.UserRepository;
import com.backend.pfe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

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
            user.setResetToken(token);
            userRepository.save(user);
            return true;
        }
        return false;
    }


    public List<User> getCollaboratorsByManagerId(Integer managerId) {
        return userRepository.findAll().stream()
                .filter(user -> user.getTeam() != null && user.getTeam().getManager().getId().equals(managerId))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> addCollaborator(User collaborator) {
        collaborator.setPassword(collaborator.getPassword());
        collaborator.setRole(Role.COLLABORATOR); // Set the role to COLLABORATOR
        userRepository.save(collaborator);

        // Prepare the email context
        Context context = new Context();
        context.setVariable("firstName", collaborator.getFirstName());
        context.setVariable("email", collaborator.getEmail());
        context.setVariable("password", collaborator.getPassword());

        // Send HTML email with access details
        emailService.sendHtmlEmail(
                collaborator.getEmail(),
                "Collaborator Account Created",
                "collaborator-account-created",
                context
        );

        return ResponseEntity.ok("added collaborator successfully");
    }



    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllByTeamId(Integer teamId) {
        return userRepository.findAllByTeamId(teamId);
    }

    @Override
    public List<User> getAllCollaborators() {
        return List.of();
    }
}