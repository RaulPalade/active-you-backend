package com.active_you.authgateway.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonRoleWrapper {
    private Person person;
    private Role role;
}
