package com.active_you.authgateway.controllers;

import com.active_you.authgateway.models.PersonRoleWrapper;
import com.active_you.authgateway.rabbitmq.RabbitMQConfig;
import com.active_you.authgateway.service.UserServiceImpl;
import com.active_you.authgateway.utils.PersonQueueMessage;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
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
            Long createdId = userServiceImpl.saveUser(personRoleWrapper);
            Gson gson = new Gson();
            PersonQueueMessage personQueueMessage = new PersonQueueMessage(personRoleWrapper, "createUser");
            Message newMessage = MessageBuilder.withBody(gson.toJson(personQueueMessage).getBytes()).build();
            Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.AUTH_USER_EXCHANGE, RabbitMQConfig.AUTH_USER_QUEUE, newMessage);

            if (result != null) {
                String correlationId = newMessage.getMessageProperties().getCorrelationId();
                HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
                String msgId = (String) headers.get("spring_returned_message_correlation");

                if (msgId.equals(correlationId)) {
                    String responseRabbit = new String(result.getBody());
                    if (Boolean.parseBoolean(responseRabbit)) {
                        response.put("message", "Registrazione avvenuta con successo");
                    } else {
                        userServiceImpl.removeUserById(createdId);
                        response.put("message", "Errore durante la registrazione");
                    }
                } else {
                    System.out.println("ERRORE");
                    userServiceImpl.removeUserById(createdId);
                    response.put("message", "Errore durante la registrazione");
                }
            }
        } catch (DataIntegrityViolationException e) {
            response.put("message", "Email già presente");
            return response;
        }


        return response;
    }
}