package com.active_you.userservice.services;

import com.active_you.userservice.models.*;
import com.active_you.userservice.repository.ExerciseRepository;
import com.active_you.userservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class PersonService {
    private final PersonRepository personRepository;
    private final ExerciseRepository exerciseRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, ExerciseRepository exerciseRepository) {
        this.personRepository = personRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public List<PersonDTO> getAllUsers() {
        return personRepository.findAll().stream().map(PersonDTO::new).toList();
    }

    public Optional<Person> getPersonById(long id) {
        return personRepository.findById(id);
    }

    public ResponseEntity<String> addPerson(Person newPerson) {
        try {
            personRepository.save(newPerson);
            return new ResponseEntity<>("Goal added successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add goal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deletePerson(Long id) {
        try {
            personRepository.deleteById(id);
            return new ResponseEntity<>("Person deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete person", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<PersonWorkout> getPersonalWorkouts(Long id) {
        List<Object[]> results = personRepository.getPersonalWorkouts(id);
        List<PersonWorkout> personalWorkouts = new ArrayList<>();

        for (Object[] result : results) {
            Workout workout = new Workout();
            workout.setId((Long) result[0]);
            workout.setCreatedById((Long) result[1]);
            workout.setName((String) result[2]);
            workout.setType((String) result[3]);

            Timestamp initDate = (Timestamp) result[4];
            Timestamp endDate = (Timestamp) result[5];
            boolean completed = (boolean) result[6];

            PersonWorkout personalWorkout = new PersonWorkout();
            personalWorkout.setWorkout(workout);
            personalWorkout.setInitDate(initDate);
            personalWorkout.setEndDate(endDate);
            personalWorkout.setCompleted(completed);

            System.out.println(workout.getId());

            Set<Exercise> exercises = exerciseRepository.findByWorkoutId(workout.getId());
            workout.setExercises(exercises);

            personalWorkouts.add(personalWorkout);
        }

        return personalWorkouts;
    }
}
