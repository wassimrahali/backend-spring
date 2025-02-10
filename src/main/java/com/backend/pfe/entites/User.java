package com.backend.pfe.entites;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private Role role;




}
