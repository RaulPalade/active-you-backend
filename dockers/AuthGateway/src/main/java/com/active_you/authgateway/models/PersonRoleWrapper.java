package com.active_you.authgateway.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonRoleWrapper {
    private Person person;
    private Role role;

    @Override
    public String toString() {
        return "{" +
                "\"person\": " + (person != null ? person.toString() : "null") + "," +
                "\"role\": " + (role != null ? role.toString() : "null") +
                "}";
    }
}
