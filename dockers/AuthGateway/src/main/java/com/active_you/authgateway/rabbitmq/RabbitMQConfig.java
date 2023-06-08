package com.active_you.authgateway.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String AUTH_USER_QUEUE = "auth_user_queue";
    public static final String USER_AUTH_REPLY = "user_auth_reply";
    public static final String AUTH_USER_EXCHANGE = "auth_user_exchange";

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
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReplyAddress(USER_AUTH_REPLY);
        template.setReplyTimeout(5000);
        return template;
    }

    @Bean
    SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(USER_AUTH_REPLY);
        container.setMessageListener(rabbitTemplate(connectionFactory));
        return container;
    }
}
