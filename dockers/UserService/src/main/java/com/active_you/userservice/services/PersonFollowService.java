package com.active_you.userservice.services;

import com.active_you.userservice.models.Person;
import com.active_you.userservice.models.PersonFollow;
import com.active_you.userservice.models.PersonFollowId;
import com.active_you.userservice.repository.PersonFollowRepository;
import com.active_you.userservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonFollowService {
    private final PersonRepository personRepository;
    private final PersonFollowRepository personFollowRepository;

    @Autowired
    public PersonFollowService(PersonRepository personRepository, PersonFollowRepository personFollowRepository) {
        this.personRepository = personRepository;
        this.personFollowRepository = personFollowRepository;
    }

    public ResponseEntity<String> followPerson(Long id1, Long id2) {
        System.out.println("FollowPerson: " + id1 + " " + id2);
        Person person1 = personRepository.findById(id1).orElse(null);
        Person person2 = personRepository.findById(id2).orElse(null);

        if (person1 == null || person2 == null) {
            return new ResponseEntity<>("Person not found", HttpStatus.NOT_FOUND);
        }

        PersonFollow personFollow = new PersonFollow(person1, person2);
        personFollow.setId(new PersonFollowId(person1.getId(), person2.getId()));
        System.out.println(personFollow);
        personFollowRepository.save(personFollow);
        return new ResponseEntity<>("Followed successfully", HttpStatus.OK);
    }

    public ResponseEntity<String> unfollowPerson(Long id1, Long id2) {
        PersonFollow personFollow = personFollowRepository.findById(new PersonFollowId(id1, id2));

        if (personFollow == null) {
            return new ResponseEntity<>("PersonFollow not found", HttpStatus.NOT_FOUND);
        }

        personFollowRepository.delete(personFollow);
        return new ResponseEntity<>("Unfollowed successfully", HttpStatus.OK);
    }

}
