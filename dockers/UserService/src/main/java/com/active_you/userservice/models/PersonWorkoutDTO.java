package com.active_you.userservice.models;

import java.sql.Timestamp;

public class PersonWorkoutDTO {
    private Workout workout;
    private Timestamp initDate;
    private Timestamp endDate;
    private boolean completed;

    public PersonWorkoutDTO(Workout workout, Timestamp initDate, Timestamp endDate, boolean completed) {
        this.workout = workout;
        this.initDate = initDate;
        this.endDate = endDate;
        this.completed = completed;
    }
}
