package com.backend.pfe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/manager")
@RequiredArgsConstructor
public class CollaboratorController {
    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ok("Hello I'm a collaborator");
    }


}
