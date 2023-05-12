package com.active_you.userservice.services;

import com.active_you.userservice.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Person> findAllByName(String name) {
        return userRepository.findAllByName(name);
    }
}
