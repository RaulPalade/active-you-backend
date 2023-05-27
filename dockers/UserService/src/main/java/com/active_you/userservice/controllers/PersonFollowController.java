package com.active_you.userservice.controllers;

import com.active_you.userservice.models.PersonFollow;
import com.active_you.userservice.services.PersonFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/personFollow")
public class PersonFollowController {
    private final PersonFollowService personFollowService;

    @Autowired
    public PersonFollowController(PersonFollowService personFollowService) {
        this.personFollowService = personFollowService;
    }

    @PostMapping("/follow")
    public ResponseEntity<String> followPerson(@RequestParam("from") Long id1, @RequestParam("to") Long id2) {
        return personFollowService.followPerson(id1, id2);
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollowPerson(@RequestParam("from") Long id1, @RequestParam("to") Long id2) {
        return personFollowService.unfollowPerson(id1, id2);
    }
}
