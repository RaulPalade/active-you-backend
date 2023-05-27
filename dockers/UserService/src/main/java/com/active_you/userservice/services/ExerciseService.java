package com.active_you.userservice.services;

import com.active_you.userservice.models.Exercise;
import com.active_you.userservice.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public ResponseEntity<String> addExercise(Exercise exercise, Long workoutId) {
        try {
            Long createdId = exerciseRepository.save(exercise).getId();
            exerciseRepository.addExerciseToWorkout(createdId, workoutId);
            return new ResponseEntity<>("Exercise saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save exercise unfollow", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> removeExercise(Long exerciseId) {
        try {
            exerciseRepository.deleteById(exerciseId);
            return new ResponseEntity<>("Exercise deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete exercise", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
