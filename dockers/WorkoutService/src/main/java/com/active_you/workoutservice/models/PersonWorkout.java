package com.active_you.workoutservice.models;

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
@Table
public class PersonWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private Long idPerson;

    @ManyToOne
    @JoinColumn(name = "id_workout")  // Colonna che fa riferimento all'ID dell'allenamento
    private Workout workout;

    private Timestamp initDate;
    private Timestamp endDate;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean completed;

    public PersonWorkout() {

    }
}
