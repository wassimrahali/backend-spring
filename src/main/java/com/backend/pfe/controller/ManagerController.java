// src/main/java/com/backend/pfe/controller/ManagerController.java
package com.backend.pfe.controller;

import com.backend.pfe.entites.Team;
import com.backend.pfe.entites.User;
import com.backend.pfe.service.TeamService;
import com.backend.pfe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final TeamService teamService;
    private final UserService userService;

    @PostMapping("/create-team")
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.createTeam(team));
    }

    @PostMapping("/create-collaborator")
    public ResponseEntity<ResponseEntity<String>> addCollaborator(@RequestBody User collaborator) {
        return ResponseEntity.ok(userService.addCollaborator(collaborator));
    }

    @GetMapping("/collaborators/{managerId}")
    public ResponseEntity<List<User>> getCollaboratorsByManagerId(@PathVariable Integer managerId) {
        return ResponseEntity.ok(userService.getCollaboratorsByManagerId(managerId));
    }



    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello I'm a manager");
    }
}