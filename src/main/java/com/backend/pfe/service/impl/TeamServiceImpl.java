// src/main/java/com/backend/pfe/service/impl/TeamServiceImpl.java
package com.backend.pfe.service.impl;

import com.backend.pfe.entites.Team;
import com.backend.pfe.repository.TeamRepository;
import com.backend.pfe.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }
}