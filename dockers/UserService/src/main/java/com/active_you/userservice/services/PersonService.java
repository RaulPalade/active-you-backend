package com.active_you.userservice.services;

import com.active_you.userservice.models.Person;
import com.active_you.userservice.models.PersonDTO;
import com.active_you.userservice.models.PersonFollow;
import com.active_you.userservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> getAllUsers() {
        return personRepository.findAll().stream().map(PersonDTO::new).toList();
    }

    public List<Person> findAllByName(String name) {
        return personRepository.findAllByName(name);
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public Person addPerson(Person newPerson) {
        return personRepository.save(newPerson);
    }

    public boolean deleteById(Long id) {
        personRepository.deleteById(id);
        return true;
    }
}
