package com.backend.pfe.controller;

import com.backend.pfe.dto.ManagerRegistrationRequest;
import com.backend.pfe.entites.User;
import com.backend.pfe.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationService authenticationService;

    @PostMapping("/create-manager")
    public ResponseEntity<User> createManager(@RequestBody ManagerRegistrationRequest request) {
        return ResponseEntity.ok(authenticationService.createManager(request));
    }

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello I'm an admin");
    }
}