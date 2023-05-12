package com.active_you.userservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int repetitions;
    private int series;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "exercise_workout",
            joinColumns = {@JoinColumn(name = "id_exercise")},
            inverseJoinColumns = {@JoinColumn(name = "id_workout")}
    )
    private Set<Workout> onWorkouts = new HashSet<>();
}
