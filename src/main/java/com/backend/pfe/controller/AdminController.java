package com.backend.pfe.controller;

import com.backend.pfe.dto.ManagerRegistrationRequest;
import com.backend.pfe.entites.User;
import com.backend.pfe.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationService authenticationService;

    @PostMapping("/create-manager")
    public ResponseEntity<User> createManager(@RequestBody ManagerRegistrationRequest request) {
        return ResponseEntity.ok(authenticationService.createManager(request));
    }

    @GetMapping("/managers")
    public ResponseEntity<List<User>> getAllManagers() {
        return ResponseEntity.ok(authenticationService.getAllManagers());
    }

    @PutMapping("/update-manager/{id}")
    public ResponseEntity<User> updateManager(@PathVariable Integer id, @RequestBody ManagerRegistrationRequest request) {
        return ResponseEntity.ok(authenticationService.updateManager(id, request));
    }

    @DeleteMapping("/delete-manager/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Integer id) {
        authenticationService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manager/{id}")
    public ResponseEntity<User> getManagerById(@PathVariable Integer id) {
        return ResponseEntity.ok(authenticationService.getManagerById(id));
    }
}