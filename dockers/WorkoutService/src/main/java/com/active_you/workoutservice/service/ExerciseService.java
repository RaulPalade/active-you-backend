package com.active_you.workoutservice.service;

import com.active_you.workoutservice.models.Exercise;
import com.active_you.workoutservice.models.Workout;
import com.active_you.workoutservice.repository.ExerciseRepository;
import com.active_you.workoutservice.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    public List<Exercise> getExercisesByWorkout(Long id) {
        Optional<Workout> workout = workoutRepository.findById(id);
        if (workout.isPresent()) {
            return exerciseRepository.findExerciseByWorkout(workout.get());
        } else {
            return new ArrayList<>();
        }
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public Exercise getExercisesById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
    }

    public ResponseEntity<String> addExercise(Exercise exercise) {
        try {
            exerciseRepository.save(exercise);
            return new ResponseEntity<>("Exercise added successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add exercise", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> removeExercise(Long id) {
        try {
            exerciseRepository.deleteById(id);
            return new ResponseEntity<>("Exercise removed successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to remove workout", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
