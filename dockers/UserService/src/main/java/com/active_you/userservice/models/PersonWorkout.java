package com.active_you.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@Entity
@Table(name = "person_workout")  // Specifica il nome della tabella associativa
public class PersonWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")  // Colonna che fa riferimento all'ID della persona
    @JsonIgnore
    private Person person;

    @ManyToOne
    @JoinColumn(name = "workout_id")  // Colonna che fa riferimento all'ID dell'allenamento
    private Workout workout;

    private Timestamp initDate;
    private Timestamp endDate;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean completed;

    public PersonWorkout() {
    }
}