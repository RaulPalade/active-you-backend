package com.active_you.userservice.utils;

import com.active_you.userservice.models.Workout;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class WorkoutMessage {
    private Workout workout;
    private String action;

    public WorkoutMessage(Workout workout, String action) {
        this.workout = workout;
        this.action = action;
    }
}
