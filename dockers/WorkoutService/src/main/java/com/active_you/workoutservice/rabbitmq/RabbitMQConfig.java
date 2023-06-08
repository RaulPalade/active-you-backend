package com.active_you.workoutservice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_WORKOUT = "workout_queue";
    public static final String TOPIC_EXCHANGE_WORKOUT = "workout_exchange";
    public static final String ROUTING_KEY_WORKOUT = "workout_key";

    public static final String QUEUE_REPLY = "reply_queue";

    @Bean
    public Queue workoutQueue() {
        return new Queue(QUEUE_WORKOUT);
    }

    @Bean
    public Queue replyQueue() {
        return new Queue(QUEUE_REPLY);
    }

    @Bean
    public TopicExchange workoutExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_WORKOUT);
    }

    @Bean
    public Binding bindingWorkout(Queue workoutQueue, TopicExchange workoutExchange) {
        return BindingBuilder.bind(workoutQueue).to(workoutExchange).with(QUEUE_WORKOUT);
    }

    @Bean
    public Binding replyBinding(Queue replyQueue, TopicExchange workoutExchange) {
        return BindingBuilder.bind(replyQueue).to(workoutExchange).with(QUEUE_REPLY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
