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
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
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

    public ResponseEntity<String> addExercise(Long id, Exercise exercise) {
        System.out.println(exercise);
        try {
            Optional<Workout> workout = workoutRepository.findById(id);
            workout.ifPresent(exercise::setWorkout);
            exerciseRepository.save(exercise);
            return new ResponseEntity<>("Exercise added successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add exercise", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Exercise> getExercisesByWorkout(Long id) {
        Optional<Workout> workout = workoutRepository.findById(id);
        if (workout.isPresent()) {
            return exerciseRepository.findExerciseByWorkout(workout.get());
        } else {
            return new ArrayList<>();
        }
    }
}
