package com.active_you.workoutservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.active_you.workoutservice.models.Workout;
import com.active_you.workoutservice.service.WorkoutService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }


    @GetMapping
    public List<Workout> getAllWorkouts(){return workoutService.findAll();}

    @GetMapping("/{id}")
    public Optional<Workout> getById(@PathVariable Long id){return workoutService.getById(id);}

    @GetMapping("/name/{name}")
    public List<Workout> getByName(@PathVariable String name){return workoutService.getByName(name);}

    @PostMapping
    public Workout addWorkout(@RequestBody Workout newWorkout){return workoutService.addWorkout(newWorkout);}

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable Long id){return workoutService.deleteById(id);}

}
