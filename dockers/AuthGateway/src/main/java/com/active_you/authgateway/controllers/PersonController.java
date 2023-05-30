package com.active_you.authgateway.controllers;

import com.active_you.authgateway.models.Person;
import com.active_you.authgateway.rabbitmq.RabbitMQConfig;
import com.active_you.authgateway.service.UserServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
public class PersonController {
    private final UserServiceImpl userServiceImpl;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PersonController(UserServiceImpl userServiceImpl, RabbitTemplate rabbitTemplate) {
        this.userServiceImpl = userServiceImpl;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/create")
    public Map<String, String> create(@RequestBody Person person) {
        Map<String, String> response = new HashMap<>();

        try {
            userServiceImpl.saveUser(person);
            rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, person);
        } catch (DataIntegrityViolationException e) {
            response.put("message", "Email già presente");
            return response;
        }

        response.put("message", "Registrazione avvenuta con successo");
        return response;
    }
}