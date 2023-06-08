package com.active_you.userservice.utils;

import com.active_you.userservice.models.PersonRoleWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonQueueMessage {
    private PersonRoleWrapper personRoleWrapper;
    private String action;

    @Override
    public String toString() {
        return "{\"personRoleWrapper\": " + (personRoleWrapper != null ? personRoleWrapper.toString() : "null") +
                ", \"action\": \"" + action + "\"}";
    }
}
