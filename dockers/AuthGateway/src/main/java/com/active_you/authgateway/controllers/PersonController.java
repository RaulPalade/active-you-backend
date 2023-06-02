package com.active_you.authgateway.controllers;

import com.active_you.authgateway.models.Person;
import com.active_you.authgateway.models.Role;
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
    public Map<String, String> create(@RequestBody Person person) {
        Map<String, String> response = new HashMap<>();

        List<Role> roles = new ArrayList<>();
        Role role1 = new Role();
        Role role2 = new Role();
        role1.setId(1L);
        role2.setId(2L);
        roles.add(role1);
        roles.add(role2);
        person.setRoles(roles);

        try {
            userServiceImpl.saveUser(person);
            rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_PERSON, RabbitMQConfig.ROUTING_KEY_PERSON, new PersonQueueMessage(person, "registerPerson"));
        } catch (DataIntegrityViolationException e) {
            response.put("message", "Email già presente");
            return response;
        }

        response.put("message", "Registrazione avvenuta con successo");
        return response;
    }
}