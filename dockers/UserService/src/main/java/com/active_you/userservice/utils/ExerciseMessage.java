package com.active_you.userservice.utils;

import com.active_you.userservice.models.Exercise;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseMessage {
    private Exercise exercise;
    private String action;

    public ExerciseMessage(Exercise exercise, String action) {
        this.exercise = exercise;
        this.action = action;
    }
}