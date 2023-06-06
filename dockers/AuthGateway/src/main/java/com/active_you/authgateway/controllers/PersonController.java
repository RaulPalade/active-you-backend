package com.active_you.authgateway.controllers;

import com.active_you.authgateway.models.PersonRoleWrapper;
import com.active_you.authgateway.rabbitmq.RabbitMQConfig;
import com.active_you.authgateway.service.UserServiceImpl;
import com.active_you.authgateway.utils.PersonQueueMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/auth")
public class PersonController {
    private final UserServiceImpl userServiceImpl;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PersonController(UserServiceImpl userServiceImpl, RabbitTemplate rabbitTemplate) {
        this.userServiceImpl = userServiceImpl;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public Map<String, String> alive() {
        Map<String, String> response = new HashMap<>();
        response.put("Status", "Live");
        return response;
    }

    @PostMapping("/create")
    public Map<String, String> create(@RequestBody PersonRoleWrapper personRoleWrapper) {
        Map<String, String> response = new HashMap<>();
        try {
            userServiceImpl.saveUser(personRoleWrapper);
            rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_PERSON, RabbitMQConfig.ROUTING_KEY_PERSON, new PersonQueueMessage(personRoleWrapper, "registerPerson"));
        } catch (DataIntegrityViolationException e) {
            response.put("message", "Email già presente");
            return response;
        }

        response.put("message", "Registrazione avvenuta con successo");
        return response;
    }
}