package com.active_you.userservice.controllers;

import com.active_you.userservice.models.Person;
import com.active_you.userservice.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllUsers() {
        return personService.getAllUsers();
    }

    @GetMapping("/{name}")
    public List<Person> findAllByName(@PathVariable String name) {
        return personService.findAllByName(name);
    }
}
