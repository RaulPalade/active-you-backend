package com.active_you.userservice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
    public static final String QUEUE_WORKOUT = "workout_queue";
    public static final String TOPIC_EXCHANGE_WORKOUT = "workout_exchange";
    public static final String ROUTING_KEY_WORKOUT = "workout_key";

    public static final String QUEUE_PERSON = "person_queue";
    public static final String TOPIC_EXCHANGE_PERSON = "person_exchange";
    public static final String ROUTING_KEY_PERSON = "person_key";


    @Bean
    public Queue workoutQueue() {
        return new Queue(QUEUE_WORKOUT);
    }

    @Bean
    public Queue personQueue() {
        return new Queue(QUEUE_PERSON);
    }

    @Bean
    public TopicExchange workoutExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_WORKOUT);
    }

    @Bean
    public TopicExchange personExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_PERSON);
    }

    @Bean
    public Binding binding(Queue workoutQueue, TopicExchange workoutExchange) {
        return BindingBuilder.bind(workoutQueue).to(workoutExchange).with(ROUTING_KEY_WORKOUT);
    }

    @Bean
    public Binding personBinding(Queue personQueue, TopicExchange personExchange) {
        return BindingBuilder.bind(personQueue).to(personExchange).with(ROUTING_KEY_PERSON);
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
