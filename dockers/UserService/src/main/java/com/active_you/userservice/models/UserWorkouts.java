package com.active_you.userservice.models;

import jakarta.persistence.*;

@Table
@Entity
public class UserWorkouts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUser;
    private Long idWorkout;
    private Boolean isActive;
}
