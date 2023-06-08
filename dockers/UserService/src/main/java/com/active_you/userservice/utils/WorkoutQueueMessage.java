package com.active_you.userservice.utils;

import com.active_you.userservice.models.Workout;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutQueueMessage {
    private Workout workout;
    private String action;

    @Override
    public String toString() {
        return "{\"workout\": " + workout.toString() + ", \"action\": \"" + action + "\"}";
    }
}