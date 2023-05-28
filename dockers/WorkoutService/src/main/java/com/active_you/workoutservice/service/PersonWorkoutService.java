package com.active_you.workoutservice.service;

import com.active_you.workoutservice.models.PersonWorkout;
import com.active_you.workoutservice.repository.PersonWorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Component
public class PersonWorkoutService {
    private final PersonWorkoutRepository personWorkoutRepository;

    @Autowired
    public PersonWorkoutService(PersonWorkoutRepository personWorkoutRepository) {
        this.personWorkoutRepository = personWorkoutRepository;
    }

    public List<PersonWorkout> getPersonWorkouts(Long personId) {
        return personWorkoutRepository.getPersonWorkoutByIdPerson(personId);
    }

    public ResponseEntity<String> saveWorkoutForUser(PersonWorkout personWorkout) {
        try {
            personWorkoutRepository.save(personWorkout);
            return new ResponseEntity<>("Person workout saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to save person workout", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteWorkoutForUser(Long id) {
        try {
            personWorkoutRepository.deleteWorkoutForUser(id, Timestamp.from(Instant.now()));
            return new ResponseEntity<>("Person workout deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete person workout", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
