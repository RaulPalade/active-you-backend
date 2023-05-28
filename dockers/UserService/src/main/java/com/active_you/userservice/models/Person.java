package com.active_you.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String password;
    private double height;
    private String heightUnit;
    private double weight;
    private String weightUnit;
    private String sex;
    private String role;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Goal> myGoals = new HashSet<>();

    @OneToMany(mappedBy = "to")
    @JsonIgnore
    private List<PersonFollow> followers;

    @OneToMany(mappedBy = "from")
    @JsonIgnore
    private List<PersonFollow> following;

    public Person() {

    }
}