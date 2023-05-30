package com.active_you.authgateway.controllers;

import com.active_you.authgateway.models.Person;
import com.active_you.authgateway.rabbitmq.RabbitMQConfig;
import com.active_you.authgateway.service.PersonService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
public class PersonController {
    private final PersonService personService;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PersonController(PersonService personService, RabbitTemplate rabbitTemplate) {
        this.personService = personService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/create")
    public Map<String, String> create(@RequestBody Person person) {
        Map<String, String> response = new HashMap<>();

        try {
            personService.saveUser(person);
            rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, person);
        } catch (DataIntegrityViolationException e) {
            response.put("message", "Email già presente");
            return response;
        }

        response.put("message", "Registrazione avvenuta con successo");
        return response;
    }
}