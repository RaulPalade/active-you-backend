package com.active_you.userservice.controllers;

import com.active_you.userservice.models.*;
import com.active_you.userservice.services.GoalService;
import com.active_you.userservice.services.PersonService;
import com.active_you.userservice.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/v1/users")
public class PersonController {
    private final PersonService personService;
    private final WorkoutService workoutService;
    private final GoalService goalService;

    @Autowired
    public PersonController(PersonService personService, WorkoutService workoutService, GoalService goalService) {
        this.personService = personService;
        this.workoutService = workoutService;
        this.goalService = goalService;
    }

    // WORKS
    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllUsers();
    }

    // WORKS
    @GetMapping("/{id}")
    public Optional<SinglePersonDTO> getPersonById(@PathVariable Long id) {
        return personService.getPersonById(Math.toIntExact(id));
    }

    // WORKS
    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person newPerson) {
        return personService.addPerson(newPerson);
    }

    // WORKS
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }

    // WORKS
    @GetMapping("/{id}/goals")
    public Set<Goal> getPersonalGoals(@PathVariable Long id) {
        return goalService.getPersonalGoals(id);
    }

    // WORKS
    @PostMapping("/{id}/goals")
    public ResponseEntity<String> addGoal(@PathVariable Long id, @RequestBody Goal goal) {
        return goalService.addGoal(id, goal);
    }

    // WORKS
    @DeleteMapping("/{id}/goals/{goalId}")
    public ResponseEntity<String> removeGoal(@PathVariable Long id, @PathVariable Long goalId) {
        return goalService.removeGoal(id, goalId);
    }

    // WORKS
    @GetMapping("/{id}/workouts")
    public Set<PersonWorkout> getPersonalWorkouts(@PathVariable Long id) {
        return personService.getPersonalWorkouts(id);
    }

    @PostMapping("/{id}/workouts")
    public ResponseEntity<String> addWorkout(@PathVariable Long id, @RequestBody Workout workout) {
        return workoutService.addWorkout(id, workout);
    }

    @PostMapping("/{id}/workouts/{workoutId}")
    public ResponseEntity<String> removeWorkout(@PathVariable Long id, @PathVariable Long workoutId) {
        return workoutService.removeWorkout(id, workoutId);
    }
}