package com.active_you.workoutservice.controller;

import com.active_you.workoutservice.models.Exercise;
import com.active_you.workoutservice.models.Workout;
import com.active_you.workoutservice.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.findAll();
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable Long id) {
        return exerciseService.getExercisesById(id);
    }

    @PostMapping
    public ResponseEntity<String> addExercise(@RequestBody Exercise exercise) {
        return exerciseService.addExercise(exercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeExercise(@PathVariable Long id) {
        return exerciseService.removeExercise(id);
    }

    @GetMapping("/workout/{id}")
    public List<Exercise> getExercisesByWorkout(@PathVariable Long id) {
        return exerciseService.getExercisesByWorkout(id);
    }
}
