package com.active_you.workoutservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table
public class Exercise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int repetitions;
    private int series;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workout_id")
    @JsonIgnore
    private Workout workout;

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id + "," +
                "\"name\": \"" + name + "\"," +
                "\"repetitions\": " + repetitions + "," +
                "\"series\": " + series +
                "}";
    }
}
