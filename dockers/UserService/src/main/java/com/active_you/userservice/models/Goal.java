package com.active_you.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Entity
@Table
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private double weight;
    private int daysPerWeek;
    private Timestamp initDate;
    private Timestamp endDate;
    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private boolean completed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    @JsonIgnore
    private Person person;

    public Goal() {

    }

    @Override
    public String toString() {
        return "{\"id\": " + id +
                ", \"name\": \"" + name + "\"" +
                ", \"type\": \"" + type + "\"" +
                ", \"weight\": " + weight +
                ", \"daysPerWeek\": " + daysPerWeek +
                ", \"initDate\": " + (initDate != null ? initDate.getTime() : "null") +
                ", \"endDate\": " + (endDate != null ? endDate.getTime() : "null") +
                ", \"completed\": " + completed +
                ", \"person\": " + (person != null ? person.toString() : "null") +
                "}";
    }
}
