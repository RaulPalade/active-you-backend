package com.active_you.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
