package com.active_you.userservice.utils;

import com.active_you.userservice.models.Exercise;
import com.active_you.userservice.models.Workout;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkoutQueueMessage {
    private Long workoutId;
    private Long createdBy;
    private Workout workout;
    private Exercise exercise;
    private String action;

    public WorkoutQueueMessage(Long createdBy, Workout workout, String action) {
        this.createdBy = createdBy;
        this.workout = workout;
        this.action = action;
    }

    public WorkoutQueueMessage(Long workoutId, Exercise exercise, String action) {
        this.workoutId = workoutId;
        this.exercise = exercise;
        this.action = action;
    }
}