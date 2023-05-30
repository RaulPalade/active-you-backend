package com.active_you.workoutservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "workout", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Exercise> exercises = new HashSet<>();
}