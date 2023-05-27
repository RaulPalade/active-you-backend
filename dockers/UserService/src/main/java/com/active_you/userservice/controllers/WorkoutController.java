package com.active_you.userservice.controllers;

import com.active_you.userservice.models.Person;
import com.active_you.userservice.models.Workout;
import com.active_you.userservice.rabbitmq.RabbitMQConfig;
import com.active_you.userservice.services.WorkoutService;
import org.aspectj.bridge.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/workouts")
public class WorkoutController {
    final WorkoutService workoutService;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/createWorkout")
    public Workout createWorkout(@RequestBody Workout workout) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, workout);
        return workout;
    }

//    @PostMapping("/saveWorkout")
//    public ResponseEntity<String> saveWorkout(@RequestBody Workout workout) {
//        return workoutService.saveWorkout(workout);
//    }
//
//    @PostMapping("/saveWorkout")
//    public ResponseEntity<String> markWorkoutCompleted(@RequestParam("personId") Long personId, @RequestParam("idWorkout") Long idWorkout) {
//        return workoutService.markWorkoutCompleted(personId, idWorkout);
//    }
}
