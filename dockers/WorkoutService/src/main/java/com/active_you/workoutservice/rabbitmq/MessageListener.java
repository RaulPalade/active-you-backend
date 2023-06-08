package com.active_you.workoutservice.rabbitmq;

import Utils.WorkoutQueueMessage;
import com.active_you.workoutservice.models.Workout;
import com.active_you.workoutservice.service.WorkoutService;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private final WorkoutService workoutService;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageListener(WorkoutService workoutService, RabbitTemplate rabbitTemplate) {
        this.workoutService = workoutService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_WORKOUT_QUEUE)
    public void process(Message message) {
        String messageBody = new String(message.getBody());
        WorkoutQueueMessage workoutQueueMessage = deserializeMessageMessageFromString(messageBody);
        System.out.println(workoutQueueMessage);

        if (workoutQueueMessage != null && workoutQueueMessage.getAction().equals("createWorkout")) {
            Workout workout = workoutQueueMessage.getWorkout();
            if (workout != null) {
                int idGenerated = workoutService.addWorkout(workout);
                if (idGenerated != -1) {
                    Message response = MessageBuilder.withBody(String.valueOf(idGenerated).getBytes()).build();
                    CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                    System.out.println(correlationData);
                    rabbitTemplate.sendAndReceive(RabbitMQConfig.USER_WORKOUT_EXCHANGE, RabbitMQConfig.WORKOUT_USER_REPLY, response, correlationData);
                }
            }
        }
    }

    private WorkoutQueueMessage deserializeMessageMessageFromString(String messageBody) {
        Gson gson = new Gson();
        return gson.fromJson(messageBody, WorkoutQueueMessage.class);
    }

}
