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


    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReplyAddress(RPC_QUEUE2);
        template.setReplyTimeout(5000);
        return template;
    }

    @Bean
    SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RPC_QUEUE2);
        container.setMessageListener(rabbitTemplate(connectionFactory));
        return container;
    }


//    public static final String QUEUE_WORKOUT = "workout_queue";
//    public static final String TOPIC_EXCHANGE_WORKOUT = "workout_exchange";
//    public static final String ROUTING_KEY_WORKOUT = "workout_key";
//
//    public static final String QUEUE_PERSON = "person_queue";
//    public static final String TOPIC_EXCHANGE_PERSON = "person_exchange";
//    public static final String ROUTING_KEY_PERSON = "person_key";
//
//    public static final String QUEUE_REPLY = "reply_queue";
//
//    @Bean
//    public Queue workoutQueue() {
//        return new Queue(QUEUE_WORKOUT);
//    }
//
//    @Bean
//    public Queue personQueue() {
//        return new Queue(QUEUE_PERSON);
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
//    public TopicExchange personExchange() {
//        return new TopicExchange(TOPIC_EXCHANGE_PERSON);
//    }
//
//    @Bean
//    public Binding bindingWorkout(Queue workoutQueue, TopicExchange workoutExchange) {
//        return BindingBuilder.bind(workoutQueue).to(workoutExchange).with(QUEUE_WORKOUT);
//    }
//
//    @Bean
//    public Binding replyBinding(Queue replyQueue, TopicExchange workoutExchange) {
//        return BindingBuilder.bind(replyQueue).to(workoutExchange).with(QUEUE_WORKOUT);
//    }
//
//    @Bean
//    public Binding personBinding(Queue personQueue, TopicExchange personExchange) {
//        return BindingBuilder.bind(personQueue).to(personExchange).with(ROUTING_KEY_PERSON);
//    }
//
//    @Bean
//    public RabbitTemplate template(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setReplyAddress(QUEUE_REPLY);
//        template.setReceiveTimeout(6000);
//        return template;
//    }
//
//    @Bean
//    SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QUEUE_REPLY);
//        container.setMessageListener(template(connectionFactory));
//        return container;
//    }
}
