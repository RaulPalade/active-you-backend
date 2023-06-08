package com.active_you.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Workout {
    private Long createdById;
    private String name;
    private String type;

    @Override
    public String toString() {
        return "{\"createdById\": " + createdById + ", \"name\": \"" + name + "\", \"type\": \"" + type + "\"}";
    }
}
