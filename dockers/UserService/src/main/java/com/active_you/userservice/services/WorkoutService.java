package com.active_you.userservice.services;

import com.active_you.userservice.models.Exercise;
import com.active_you.userservice.models.Person;
import com.active_you.userservice.models.PersonWorkout;
import com.active_you.userservice.models.Workout;
import com.active_you.userservice.repository.ExerciseRepository;
import com.active_you.userservice.repository.PersonRepository;
import com.active_you.userservice.repository.PersonWorkoutRepository;
import com.active_you.userservice.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Component
public class WorkoutService {
    private final WorkoutRepository workoutRepository;
    final PersonRepository personRepository;
    final ExerciseRepository exerciseRepository;
    final PersonWorkoutRepository personWorkoutRepository;

    @Autowired
    public WorkoutService(WorkoutRepository workoutRepository, PersonRepository personRepository, ExerciseRepository exerciseRepository, PersonWorkoutRepository personWorkoutRepository) {
        this.workoutRepository = workoutRepository;
        this.personRepository = personRepository;
        this.exerciseRepository = exerciseRepository;
        this.personWorkoutRepository = personWorkoutRepository;
    }

    public Optional<Workout> getWorkoutById(Long id) {
        return workoutRepository.findById(id);
    }

    public ResponseEntity<String> addWorkout(Long id, Workout workout) {
        try {
            Optional<Person> person = personRepository.findById(id);
            if (person.isPresent()) {
                workoutRepository.save(workout);
                workout.getExercises().forEach(exercise -> addExercise(exercise, workout.getId()));
                PersonWorkout personWorkout = new PersonWorkout(person.get(), workout, Timestamp.from(Instant.now()), null, false);
                personWorkoutRepository.save(personWorkout);
            }
            return new ResponseEntity<>("Workout saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save workout unfollow", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> removeWorkout(Long id, Long workoutId) {
        try {
            Optional<Person> person = personRepository.findById(id);
            Optional<Workout> workout = getWorkoutById(workoutId);
            if (person.isPresent() && workout.isPresent()) {
                personWorkoutRepository.removeWorkout(person.get(), workout.get(), Timestamp.from(Instant.now()));
            }
            return new ResponseEntity<>("Workout saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save workout unfollow", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void addExercise(Exercise exercise, Long workoutId) {
        try {
            Long createdId = exerciseRepository.save(exercise).getId();
            exerciseRepository.addExerciseToWorkout(createdId, workoutId);
            new ResponseEntity<>("Exercise saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            new ResponseEntity<>("Failed to save exercise unfollow", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
