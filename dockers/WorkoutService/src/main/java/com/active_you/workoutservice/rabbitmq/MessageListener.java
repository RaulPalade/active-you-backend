package com.active_you.workoutservice.rabbitmq;

import Utils.QueueMessage;
import com.active_you.workoutservice.models.Exercise;
import com.active_you.workoutservice.models.Workout;
import com.active_you.workoutservice.service.WorkoutService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private final WorkoutService workoutService;

    @Autowired
    public MessageListener(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_WORKOUT)
    public void listener(QueueMessage message) {
        if (message.getAction().equals("createWorkout")) {
            Long createdBy = message.getCreatedBy();
            Workout workout = message.getWorkout();
            if (workout != null) {
                System.out.println("CREATE WORKOUT MESSAGE");
                workoutService.addWorkout(createdBy, workout);
            }
        } else if (message.getAction().equals("createExercise")) {
            Long workoutId = message.getWorkoutId();
            Exercise exercise = message.getExercise();
            if (exercise != null) {
                System.out.println("CREATE EXERCISE MESSAGE");
                workoutService.addExercise(workoutId, exercise);
            }
        }
    }
}
