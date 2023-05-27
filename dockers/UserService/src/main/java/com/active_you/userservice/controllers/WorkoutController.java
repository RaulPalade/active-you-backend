package com.active_you.userservice.controllers;

import com.active_you.userservice.models.Workout;
import com.active_you.userservice.rabbitmq.RabbitMQConfig;
import com.active_you.userservice.services.WorkoutService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}