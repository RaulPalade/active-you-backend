package com.active_you.userservice.models;

import jakarta.persistence.*;

@Entity
@Table
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String sex;
    private String role;
}
