package com.active_you.userservice.services;


import com.active_you.userservice.models.Workout;
import com.active_you.userservice.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class WorkoutService {
    private final WorkoutRepository workoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

//    public ResponseEntity<String> saveWorkout(Workout workout) {
//        try {
//            workoutRepository.save(workout);
//            return new ResponseEntity<>("Workout saved successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Failed to save workout unfollow", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    public ResponseEntity<String> markWorkoutCompleted(Long personId, Long idWorkout) {
//        try {
//            workoutRepository.markWorkoutCompleted(personId, idWorkout);
//            return new ResponseEntity<>("Workout marked as completed", HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Failed to mark workout as completed", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
