package com.active_you.workoutservice.service;

import com.active_you.workoutservice.models.Workout;
import com.active_you.workoutservice.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    public Workout getWorkoutById(Long id) {
        return workoutRepository.findById(id).orElse(null);
    }

    public ResponseEntity<String> addWorkout(Workout workout) {
        try {
            workoutRepository.save(workout);
            return new ResponseEntity<>("Workout added successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add workout", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> removeWorkout(Long id) {
        try {
            workoutRepository.deleteById(id);
            return new ResponseEntity<>("Workout removed successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to remove workout", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
