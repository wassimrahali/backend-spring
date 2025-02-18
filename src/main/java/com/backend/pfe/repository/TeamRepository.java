// src/main/java/com/backend/pfe/repository/TeamRepository.java
package com.backend.pfe.repository;

import com.backend.pfe.entites.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}