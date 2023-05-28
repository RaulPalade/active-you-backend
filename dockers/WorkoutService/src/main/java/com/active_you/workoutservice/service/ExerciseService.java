package com.active_you.workoutservice.service;

import com.active_you.workoutservice.models.Exercise;
import com.active_you.workoutservice.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public Exercise getExercisesById(Long id) {
        return exerciseRepository.findById(id).orElse(null);
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
