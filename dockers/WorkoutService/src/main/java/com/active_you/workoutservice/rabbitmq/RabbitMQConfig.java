package com.active_you.workoutservice.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String RPC_QUEUE1 = "queue_1";
    public static final String RPC_QUEUE2 = "queue_2";
    public static final String RPC_EXCHANGE = "rpc_exchange";

    @Bean
    Queue msgQueue() {
        return new Queue(RPC_QUEUE1);
    }

    @Bean
    Queue replyQueue() {
        return new Queue(RPC_QUEUE2);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(RPC_EXCHANGE);
    }

    @Bean
    Binding msgBinding() {
        return BindingBuilder.bind(msgQueue()).to(exchange()).with(RPC_QUEUE1);
    }

    @Bean
    Binding replyBinding() {
        return BindingBuilder.bind(replyQueue()).to(exchange()).with(RPC_QUEUE2);
    }


//    public static final String QUEUE_WORKOUT = "workout_queue";
//    public static final String TOPIC_EXCHANGE_WORKOUT = "workout_exchange";
//    public static final String ROUTING_KEY_WORKOUT = "workout_key";
//
//    public static final String QUEUE_REPLY = "reply_queue";
//
//    @Bean
//    public Queue workoutQueue() {
//        return new Queue(QUEUE_WORKOUT);
//    }
//
//    @Bean
//    public Queue replyQueue() {
//        return new Queue(QUEUE_REPLY);
//    }
//
//    @Bean
//    public TopicExchange workoutExchange() {
//        return new TopicExchange(TOPIC_EXCHANGE_WORKOUT);
//    }
//
//    @Bean
//    public Binding bindingWorkout(Queue workoutQueue, TopicExchange workoutExchange) {
//        return BindingBuilder.bind(workoutQueue).to(workoutExchange).with(QUEUE_WORKOUT);
//    }
//
//    @Bean
//    public Binding replyBinding(Queue replyQueue, TopicExchange workoutExchange) {
//        return BindingBuilder.bind(replyQueue).to(workoutExchange).with(QUEUE_REPLY);
//    }
//
//    @Bean
//    public MessageConverter messageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public AmqpTemplate template(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(messageConverter());
//        return template;
//    }
}
