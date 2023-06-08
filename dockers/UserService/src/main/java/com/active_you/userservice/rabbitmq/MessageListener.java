package com.active_you.userservice.rabbitmq;

import com.active_you.userservice.models.PersonRoleWrapper;
import com.active_you.userservice.services.PersonService;
import com.active_you.userservice.utils.PersonQueueMessage;
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
    private final PersonService personService;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageListener(PersonService personService, RabbitTemplate rabbitTemplate) {
        this.personService = personService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.AUTH_USER_QUEUE)
    public void listener(Message message) {
        String messageBody = new String(message.getBody());
        PersonQueueMessage personQueueMessage = deserializeMessageMessageFromString(messageBody);

        if (personQueueMessage.getAction().equals("createUser")) {
            PersonRoleWrapper newPerson = personQueueMessage.getPersonRoleWrapper();
            if (newPerson != null) {
                Boolean result = personService.addPerson(newPerson);
                Message response = MessageBuilder.withBody(String.valueOf(result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.AUTH_USER_EXCHANGE, RabbitMQConfig.USER_AUTH_REPLY, response, correlationData);
            }
        }
    }

    private PersonQueueMessage deserializeMessageMessageFromString(String messageBody) {
        Gson gson = new Gson();
        return gson.fromJson(messageBody, PersonQueueMessage.class);
    }
}
