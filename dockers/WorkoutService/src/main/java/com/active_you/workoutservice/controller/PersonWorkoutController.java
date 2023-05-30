package com.active_you.workoutservice.controller;

import com.active_you.workoutservice.models.PersonWorkout;
import com.active_you.workoutservice.service.PersonWorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/personWorkouts")
public class PersonWorkoutController {
    private final PersonWorkoutService personWorkoutService;

    @Autowired
    public PersonWorkoutController(PersonWorkoutService personWorkoutService) {
        this.personWorkoutService = personWorkoutService;
    }

    @GetMapping
    public List<PersonWorkout> getPersonWorkouts(@RequestParam Long personId) {
        return personWorkoutService.getPersonWorkouts(personId);
    }

    @PostMapping
    public ResponseEntity<String> saveWorkoutForUser(@RequestBody PersonWorkout personWorkout) {
        return personWorkoutService.saveWorkoutForUser(personWorkout);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> deleteWorkoutForUser(@PathVariable Long id) {
        return personWorkoutService.deleteWorkoutForUser(id);
    }
}
