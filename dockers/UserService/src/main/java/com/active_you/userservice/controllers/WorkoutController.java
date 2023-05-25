package com.active_you.userservice.controllers;

import com.active_you.userservice.models.Workout;
import com.active_you.userservice.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/workouts")
public class WorkoutController {
    final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/createWorkout")
    public Workout createWorkout(@RequestBody Workout workout) {
        //TODO
        return null;
    }

    @PostMapping("/saveWorkout")
    public Workout saveWorkout(@RequestBody Workout workout) {
        //TODO
        return null;
    }

    @PostMapping("/saveWorkout/{id}")
    public Workout markWorkoutCompleted(@PathVariable Long id) {
        // TODO
        return null;
    }
}
