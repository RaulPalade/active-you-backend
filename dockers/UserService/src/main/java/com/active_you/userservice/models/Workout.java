package com.active_you.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@Entity
@Table
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonWorkout> personWorkouts = new HashSet<>();

    @ManyToMany(mappedBy = "onWorkouts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Exercise> exercises = new HashSet<>();

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_id")
    private Person createdBy;

    public Workout() {

    }
}