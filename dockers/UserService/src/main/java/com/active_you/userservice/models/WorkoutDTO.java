package com.active_you.userservice.models;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
public class WorkoutDTO {
    private Long id;
    private String name;
    private String type;
    private Set<Exercise> exercises;
    private Long createdBy;

    public WorkoutDTO(Workout workout) {
        this.id = workout.getId();
        this.name = workout.getName();
        this.type = workout.getType();
        this.exercises = workout.getExercises();
        this.createdBy = workout.getCreatedBy().getId();
    }
}
