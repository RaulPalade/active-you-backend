package com.active_you.authgateway.controllers;

import com.active_you.authgateway.models.Person;
import com.active_you.authgateway.models.PersonRoleWrapper;
import com.active_you.authgateway.models.Role;
import com.active_you.authgateway.rabbitmq.RabbitMQConfig;
import com.active_you.authgateway.service.UserServiceImpl;
import com.active_you.authgateway.utils.PersonQueueMessage;
import com.active_you.authgateway.utils.SessionManagement;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import com.google.api.client.http.javanet.NetHttpTransport;

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

    @PostMapping("/create/google")
    public Map<String, String> createGoogle(@RequestBody Map<String, String> jwtMap) {
        Map<String, String> response = new HashMap<>();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList("21970876080-s7n97ulkjtekvgum42p3m408sm00taea.apps.googleusercontent.com")).build();
        try {
            GoogleIdToken idToken = verifier.verify(jwtMap.get("token"));
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                String name = (String) payload.get("given_name");
                String surname = (String) payload.get("family_name");
                Person person = userServiceImpl.findPersonByEmail(email);

                if (person == null) {
                    Person newPerson = new Person();
                    newPerson.setEmail(email);
                    newPerson.setName(name);
                    newPerson.setSurname(surname);

                    Role role = new Role();
                    role.setId(1L);
                    PersonRoleWrapper personRoleWrapper = new PersonRoleWrapper(newPerson, role);
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
                } else {
                    return SessionManagement.getJWTResponse(person);
                }
            } else {
                response.put("error", "Invalid Token");
            }
        } catch (Exception e) {
            response.put("error", "Generic Error");
        }

        return response;
    }
}