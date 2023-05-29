package com.active_you.userservice.controllers;

import com.active_you.userservice.utils.QueueMessage;
import com.active_you.userservice.models.*;
import com.active_you.userservice.rabbitmq.RabbitMQConfig;
import com.active_you.userservice.services.GoalService;
import com.active_you.userservice.services.PersonService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/v1/users")
public class PersonController {
    private final PersonService personService;
    private final GoalService goalService;
    private final RabbitTemplate rabbitTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonController(PersonService personService, GoalService goalService, RabbitTemplate rabbitTemplate, PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.goalService = goalService;
        this.rabbitTemplate = rabbitTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<SinglePersonDTO> getPersonById(@PathVariable Long id) {
        return personService.getPersonById(Math.toIntExact(id));
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person newPerson) {
        newPerson.setPassword(passwordEncoder.encode(newPerson.getPassword()));
        return personService.addPerson(newPerson);
    }

    @GetMapping("/me")
    public ResponseEntity<Person> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Person user = personService.findByEmail(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }

    @GetMapping("/{id}/goals")
    public Set<Goal> getPersonalGoals(@PathVariable Long id) {
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
    public void createWorkout(@RequestParam Long createdBy, @RequestBody Workout workout) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, new QueueMessage(createdBy, workout, "createWorkout"));
    }

    @PostMapping("/createExercise")
    public void createExercise(@RequestParam Long workoutId, @RequestBody Exercise exercise) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, new QueueMessage(workoutId, exercise, "createExercise"));
    }
}