package com.active_you.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @JsonIgnore
    @ManyToMany(mappedBy = "myWorkouts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Person> persons = new HashSet<>();

    @ManyToMany(mappedBy = "onWorkouts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Exercise> exercises = new HashSet<>();
}