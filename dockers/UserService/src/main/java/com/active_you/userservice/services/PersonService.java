package com.active_you.userservice.services;

import com.active_you.userservice.models.Person;
import com.active_you.userservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public Optional<Person> findById(Integer id) {
        return personRepository.findById(Long.valueOf(id));
    }

    public Person addPerson(Person newPerson) {
        return personRepository.save(newPerson);
    }

    public boolean deleteById(Long id){personRepository.deleteById(id);
        return true;
    }

    public void addFollower(Long id1, Long id2) {
        personRepository.addFollower(id1,id2);
    }

    public void removeFollower(Long id1, Long id2) {
        personRepository.removeFollower(id1,id2);
    }
}
