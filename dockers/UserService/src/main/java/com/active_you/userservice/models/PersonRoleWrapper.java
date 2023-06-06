package com.active_you.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonRoleWrapper {
    private Person person;
    private Role role;
}
