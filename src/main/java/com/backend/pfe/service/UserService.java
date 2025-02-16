package com.backend.pfe.service;

import com.backend.pfe.entites.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();
    User addCollaborator(User collaborator);
    Optional<User> findByEmail(String email);
    List<User> findAllByTeamId(Integer teamId);
}