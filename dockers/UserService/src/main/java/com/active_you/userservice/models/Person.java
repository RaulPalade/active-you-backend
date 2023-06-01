package com.active_you.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
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

    @ManyToMany
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person"), inverseJoinColumns = @JoinColumn(name = "role"))
    @OrderBy(value = "id")
    @JsonIgnore
    private List<Role> roles;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Goal> myGoals;

    @OneToMany(mappedBy = "to")
    @JsonIgnore
    private List<PersonFollow> followers;

    @OneToMany(mappedBy = "from")
    @JsonIgnore
    private List<PersonFollow> following;

    public Person() {

    }
}