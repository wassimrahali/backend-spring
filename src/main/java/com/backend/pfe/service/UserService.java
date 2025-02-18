package com.backend.pfe.service;

import com.backend.pfe.entites.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();
    ResponseEntity<String> addCollaborator(User collaborator);
    Optional<User> findByEmail(String email);
    List<User> findAllByTeamId(Integer teamId);
    List<User> getAllCollaborators(); // New method
    List<User> getCollaboratorsByManagerId(Integer managerId); // New method

}