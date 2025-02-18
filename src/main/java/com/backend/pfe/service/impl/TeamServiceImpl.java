// src/main/java/com/backend/pfe/service/impl/TeamServiceImpl.java
package com.backend.pfe.service.impl;

import com.backend.pfe.entites.Team;
import com.backend.pfe.entites.User;
import com.backend.pfe.repository.TeamRepository;
import com.backend.pfe.repository.UserRepository;
import com.backend.pfe.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Override
    public Team createTeam(Team team) {
        // Fetch the complete User object from the database
        User manager = userRepository.findById(Long.valueOf(team.getManager().getId()))
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        team.setManager(manager);
        return teamRepository.save(team);
    }
}