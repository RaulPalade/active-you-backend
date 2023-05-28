package com.active_you.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Workout {
    private Long createdBy;
    private String name;
    private String type;
}
