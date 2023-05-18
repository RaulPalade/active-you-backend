package com.active_you.userservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
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
    private Person person;
}
