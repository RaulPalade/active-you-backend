package com.active_you.userservice.services;

import com.active_you.userservice.models.*;
import com.active_you.userservice.repository.PersonRepository;
import com.active_you.userservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public List<AllPersonDTO> getAllUsers() {
        return personRepository.findAll().stream().map(AllPersonDTO::new).toList();
    }

    public Optional<SinglePersonDTO> getPersonById(long id) {
        return personRepository.findById(id).map(SinglePersonDTO::new);
    }

    public Optional<SinglePersonDTO> getPersonByEmail(String email) {
        return personRepository.findByEmail(email).map(SinglePersonDTO::new);
    }


    public Boolean addPerson(PersonRoleWrapper personRoleWrapper) {
        try {
            Optional<Role> role = roleRepository.findByName(personRoleWrapper.getRole().getName());
            Role newRole = new Role();
            role.ifPresent(value -> newRole.setId(value.getId()));

            Person newPerson = personRoleWrapper.getPerson();
            newPerson.setRoles(new ArrayList<>());
            newPerson.getRoles().add(newRole);
            System.out.println(newPerson);
            personRepository.save(newPerson);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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