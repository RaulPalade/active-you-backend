package com.active_you.userservice.utils;

import com.active_you.userservice.models.PersonRoleWrapper;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonQueueMessage {
    private PersonRoleWrapper personRoleWrapper;
    private String action;

    public PersonQueueMessage(PersonRoleWrapper personRoleWrapper, String action) {
        this.personRoleWrapper = personRoleWrapper;
        this.action = action;
    }
}
