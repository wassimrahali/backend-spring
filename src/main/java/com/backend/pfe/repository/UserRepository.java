package com.backend.pfe.repository;

import com.backend.pfe.entites.Role;
import com.backend.pfe.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByRole(Role email);
    Optional<User> findByResetToken(String resetToken);
    List<User> findAllByTeamId(Integer teamId);

}