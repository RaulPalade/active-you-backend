package com.active_you.workoutservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long createdById;
    private String name;
    private String type;
    private Timestamp initDate;
    private Timestamp endDate;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean completed = false;

    @ManyToMany(mappedBy = "onWorkouts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Exercise> exercises = new HashSet<>();
}