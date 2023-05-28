package com.active_you.userservice.controllers;

import com.active_you.userservice.utils.ExerciseMessage;
import com.active_you.userservice.utils.WorkoutMessage;
import com.active_you.userservice.models.*;
import com.active_you.userservice.rabbitmq.RabbitMQConfig;
import com.active_you.userservice.services.GoalService;
import com.active_you.userservice.services.PersonService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public List<Person> getAllPersons() {
        return personService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<SinglePersonDTO> getPersonById(@PathVariable Long id) {
        return personService.getPersonById(Math.toIntExact(id));
    }

    @PostMapping
    public ResponseEntity<String> addPerson(@RequestBody Person newPerson) {
        return personService.addPerson(newPerson);
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
    public void createWorkout(@RequestBody Workout workout) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, new WorkoutMessage(workout, "createWorkout"));
    }

    @PostMapping("/createExercise")
    public void createExercise(@RequestBody Exercise exercise) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, new ExerciseMessage(exercise, "createExercise"));
    }
}