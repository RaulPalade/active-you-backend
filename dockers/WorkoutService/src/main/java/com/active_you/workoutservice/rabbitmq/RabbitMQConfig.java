package com.active_you.workoutservice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String USER_WORKOUT_QUEUE = "user_workout_queue";
    public static final String WORKOUT_USER_REPLY = "workout_user_reply";
    public static final String USER_WORKOUT_EXCHANGE = "user_workout_exchange";

    @Bean
    Queue userWorkoutQueue() {
        return new Queue(USER_WORKOUT_QUEUE);
    }

    @Bean
    Queue workoutUserReply() {
        return new Queue(WORKOUT_USER_REPLY);
    }

    @Bean
    TopicExchange workoutUserExchange() {
        return new TopicExchange(USER_WORKOUT_EXCHANGE);
    }

    @Bean
    Binding userWorkoutBinding() {
        return BindingBuilder.bind(userWorkoutQueue()).to(workoutUserExchange()).with(USER_WORKOUT_QUEUE);
    }

    @Bean
    Binding workoutUserReplyBinding() {
        return BindingBuilder.bind(workoutUserReply()).to(workoutUserExchange()).with(WORKOUT_USER_REPLY);
    }
}
