package com.active_you.userservice.services;

import com.active_you.userservice.models.*;
import com.active_you.userservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public Optional<SinglePersonDTO> getPersonById(long id) {
        return personRepository.findById(id).map(SinglePersonDTO::new);
    }

    public ResponseEntity<String> addPerson(Person newPerson) {
        try {
            personRepository.save(newPerson);
            return new ResponseEntity<>("Person added successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to add person", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deletePerson(Long id) {
        try {
            personRepository.deleteById(id);
            return new ResponseEntity<>("Person deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to delete person", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}