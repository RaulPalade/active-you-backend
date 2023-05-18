package com.active_you.workoutservice.rabbitmq;

import com.active_you.workoutservice.service.ExerciseService;
import com.active_you.workoutservice.service.WorkoutService;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private WorkoutService workoutService;
    private ExerciseService exerciseService;
}
