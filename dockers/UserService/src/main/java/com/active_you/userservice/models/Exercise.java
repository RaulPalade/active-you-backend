package com.active_you.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
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
    @JsonIgnore
    private Set<Workout> onWorkouts = new HashSet<>();
}
