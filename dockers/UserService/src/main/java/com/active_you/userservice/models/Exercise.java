package com.active_you.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Exercise {
    private String name;
    private int repetitions;
    private int series;

    @Override
    public String toString() {
        return "{\"name\": \"" + name + "\", \"repetitions\": " + repetitions + ", \"series\": " + series + "}";
    }
}
