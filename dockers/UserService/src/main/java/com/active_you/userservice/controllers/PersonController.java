package com.active_you.userservice.controllers;

import com.active_you.userservice.utils.WorkoutQueueMessage;
import com.active_you.userservice.models.*;
import com.active_you.userservice.rabbitmq.RabbitMQConfig;
import com.active_you.userservice.services.GoalService;
import com.active_you.userservice.services.PersonService;
import com.google.gson.Gson;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/users")
public class PersonController {
    private final PersonService personService;
    private final GoalService goalService;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PersonController(PersonService personService, GoalService goalService, RabbitTemplate rabbitTemplate) {
        this.personService = personService;
        this.goalService = goalService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public List<AllPersonDTO> getAllPersons() {
        return personService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<SinglePersonDTO> getPersonById(@PathVariable Long id) {
        return personService.getPersonById(Math.toIntExact(id));
    }

    @GetMapping("/emails")
    public Optional<SinglePersonDTO> getPersonByEmail(@RequestParam String email) {
        return personService.getPersonByEmail(email);
    }


    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody PersonRoleWrapper personRoleWrapper) {
        return personService.addPerson(personRoleWrapper);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }

    @GetMapping("/{id}/goals")
    public List<Goal> getPersonalGoals(@PathVariable Long id) {
        return goalService.getPersonalGoals(id);
    }

    @PostMapping("/{id}/goals")
    public ResponseEntity<String> addGoal(@PathVariable Long id, @RequestBody Goal goal) {
        return goalService.addGoal(id, goal);
    }

    @DeleteMapping("/{id}/goals/{goalId}")
    public ResponseEntity<String> removeGoal(@PathVariable Long id, @PathVariable Long goalId) {
        return goalService.removeGoal(id, goalId);
    }

    @PostMapping("/createWorkout")
    public int createWorkout(@RequestBody Workout workout) {
        Gson gson = new Gson();
        WorkoutQueueMessage workoutQueueMessage = new WorkoutQueueMessage(workout, "createWorkout");
        Message newMessage = MessageBuilder.withBody(gson.toJson(workoutQueueMessage).getBytes()).build();
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.USER_WORKOUT_EXCHANGE, RabbitMQConfig.USER_WORKOUT_QUEUE, newMessage);

        if (result != null) {
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            String msgId = (String) headers.get("spring_returned_message_correlation");

            if (msgId.equals(correlationId)) {
                String response = new String(result.getBody());
                return Integer.parseInt(response);
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}