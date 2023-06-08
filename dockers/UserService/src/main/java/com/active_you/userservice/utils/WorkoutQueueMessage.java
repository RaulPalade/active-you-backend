package com.active_you.userservice.utils;

import com.active_you.userservice.models.Exercise;
import com.active_you.userservice.models.Workout;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutQueueMessage {
    private Workout workout;
    private Exercise exercise;
    private Long workoutIdForExercise;
    private String action;

    @Override
    public String toString() {
        return "{" +
                "\"workout\": " + (workout != null ? workout.toString() : "null") + "," +
                "\"exercise\": " + (exercise != null ? exercise.toString() : "null") + "," +
                "\"workoutId\": " + (workoutIdForExercise != null ? workoutIdForExercise : "null") + "," +
                "\"action\": \"" + action + "\"" +
                "}";
    }
}