package com.active_you.userservice.services;

import com.active_you.userservice.models.PersonFollow;
import com.active_you.userservice.repository.PersonFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonFollowService {
    private final PersonFollowRepository personFollowRepository;

    @Autowired
    public PersonFollowService(PersonFollowRepository personFollowRepository) {
        this.personFollowRepository = personFollowRepository;
    }

    public ResponseEntity<String> followPerson(PersonFollow personFollow) {
        try {
            personFollowRepository.save(personFollow);
            return new ResponseEntity<>("Unfollowed successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to unfollow", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> unfollowPerson(Long fromId, Long toId) {
        try {
            personFollowRepository.unfollow(fromId, toId);
            return new ResponseEntity<>("Unfollowed successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to unfollow", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
