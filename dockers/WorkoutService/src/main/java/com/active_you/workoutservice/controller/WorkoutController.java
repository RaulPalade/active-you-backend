package com.active_you.workoutservice.controller;

import com.active_you.workoutservice.models.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.active_you.workoutservice.models.Workout;
import com.active_you.workoutservice.service.WorkoutService;

import java.util.List;

@RestController
@RequestMapping("api/v1/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return workoutService.findAll();
    }

    @GetMapping("/{id}")
    public Workout getWorkoutById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id);
    }

    @PostMapping
    public ResponseEntity<String> addWorkout(@RequestBody Workout workout) {
        return workoutService.addWorkout(workout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeWorkout(@PathVariable Long id) {
        return workoutService.removeWorkout(id);
    }

    @PostMapping("{id}/exercises")
    public ResponseEntity<String> addExercise(@PathVariable Long id, @RequestBody Exercise exercise) {
        return workoutService.addExercise(id, exercise);
    }

    @GetMapping("/{id}/exercises")
    public List<Exercise> getExercisesByWorkout(@PathVariable Long id) {
        return workoutService.getExercisesByWorkout(id);
    }
}
