package com.active_you.userservice.utils;

import com.active_you.userservice.models.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonQueueMessage {
    private Person person;
    private String action;

    public PersonQueueMessage(Person person, String action) {
        this.person = person;
        this.action = action;
    }
}
