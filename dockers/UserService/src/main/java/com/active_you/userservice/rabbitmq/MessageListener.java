package com.active_you.userservice.rabbitmq;

import com.active_you.userservice.models.PersonRoleWrapper;
import com.active_you.userservice.services.PersonService;
import com.active_you.userservice.utils.PersonQueueMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    private final PersonService personService;

    @Autowired
    public MessageListener(PersonService personService) {
        this.personService = personService;
    }

//    @RabbitListener(queues = RabbitMQConfig.QUEUE_PERSON)
//    public void listener(PersonQueueMessage message) {
//        if (message.getAction().equals("registerPerson")) {
//            PersonRoleWrapper newPerson = message.getPersonRoleWrapper();
//            if (newPerson != null) {
//                personService.addPerson(newPerson);
//            }
//        }
//    }
}
