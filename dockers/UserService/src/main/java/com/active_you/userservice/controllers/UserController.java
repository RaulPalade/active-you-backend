package com.active_you.userservice.controllers;

import com.active_you.userservice.models.Person;
import com.active_you.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{name}")
    public List<Person> findAllByName(@PathVariable String name) {
        return userService.findAllByName(name);
    }
}
