package com.active_you.userservice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String AUTH_USER_QUEUE = "auth_user_queue";
    public static final String USER_AUTH_REPLY = "user_auth_reply";
    public static final String AUTH_USER_EXCHANGE = "auth_user_exchange";

    public static final String USER_WORKOUT_QUEUE = "user_workout_queue";
    public static final String WORKOUT_USER_REPLY = "workout_user_reply";
    public static final String USER_WORKOUT_EXCHANGE = "user_workout_exchange";

    @Bean
    Queue authUserQueue() {
        return new Queue(AUTH_USER_QUEUE);
    }

    @Bean
    Queue userAuthReply() {
        return new Queue(USER_AUTH_REPLY);
    }

    @Bean
    TopicExchange authUserExchange() {
        return new TopicExchange(AUTH_USER_EXCHANGE);
    }

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

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReplyAddress(WORKOUT_USER_REPLY);
        template.setReplyTimeout(5000);
        return template;
    }

    @Bean
    SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(WORKOUT_USER_REPLY);
        container.setMessageListener(rabbitTemplate(connectionFactory));
        return container;
    }
}