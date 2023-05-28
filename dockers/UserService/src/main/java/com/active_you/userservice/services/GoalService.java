package com.active_you.userservice.services;

import com.active_you.userservice.models.Goal;
import com.active_you.userservice.models.Person;
import com.active_you.userservice.repository.GoalRepository;
import com.active_you.userservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@Component
public class GoalService {
    final GoalRepository goalRepository;
    final PersonRepository personRepository;

    @Autowired
    public GoalService(GoalRepository goalRepository, PersonRepository personRepository) {
        this.goalRepository = goalRepository;
        this.personRepository = personRepository;
    }

    public Optional<Goal> getGoalById(Long id) {
        return goalRepository.findById(id);
    }

    public ResponseEntity<String> addGoal(Long id, Goal goal) {
        try {
            Optional<Person> person = personRepository.findById(id);
            person.ifPresent(goal::setPerson);
            System.out.println(goal);
            goalRepository.save(goal);
            return new ResponseEntity<>("Goal added successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add goal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> removeGoal(Long id, Long goalId) {
        try {
            Optional<Person> person = personRepository.findById(id);
            Optional<Goal> goal = getGoalById(goalId);
            if (person.isPresent() && goal.isPresent()) {
                goalRepository.removeGoal(person.get(), goalId, Timestamp.from(Instant.now()));
            }
            return new ResponseEntity<>("Goal deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete goal", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Set<Goal> getPersonalGoals(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return goalRepository.findGoalByPerson(person.get());
        } else {
            return new HashSet<>();
        }
    }
}
