package com.active_you.userservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@Entity
@Table
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String sex;
    private String role;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_workout",
            joinColumns = {@JoinColumn(name = "id_person")},
            inverseJoinColumns = {@JoinColumn(name = "id_workout")}
    )
    private Set<Workout> myWorkouts = new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Goal> myGoals = new HashSet<>();

    @OneToMany(mappedBy = "to")
    private List<PersonFollow> followers;

    @OneToMany(mappedBy = "from")
    private List<PersonFollow> following;

    public Person() {

    }
}