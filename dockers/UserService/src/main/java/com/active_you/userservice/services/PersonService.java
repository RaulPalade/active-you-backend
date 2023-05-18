package com.active_you.userservice.services;

import com.active_you.userservice.models.Person;
import com.active_you.userservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllUsers() {
        return personRepository.findAll();
    }

    public List<Person> findAllByName(String name) {
        return personRepository.findAllByName(name);
    }

    public Person addPerson(Person newPerson) {
        return personRepository.save(newPerson);
    }
}
