package com.active_you.userservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String sex;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    private double weight;
    private String weightUnit;
    private double height;
    private String heightUnit;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person"), inverseJoinColumns = @JoinColumn(name = "role"))
    @OrderBy(value = "id")
    private List<Role> roles;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Goal> myGoals;

    @OneToMany(mappedBy = "to", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PersonFollow> followers;

    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PersonFollow> following;

    public Person() {

    }

    @Override
    public String toString() {
        return "{\"id\": " + id +
                ", \"name\": \"" + name + "\"" +
                ", \"surname\": \"" + surname + "\"" +
                ", \"email\": \"" + email + "\"" +
                ", \"sex\": \"" + sex + "\"" +
                ", \"dateOfBirth\": " + dateOfBirth.getTime() +
                ", \"weight\": " + weight +
                ", \"weightUnit\": \"" + weightUnit + "\"" +
                ", \"height\": " + height +
                ", \"heightUnit\": \"" + heightUnit + "\"" +
                ", \"roles\": " + (roles != null ? roles.toString() : "null") +
                ", \"myGoals\": " + (myGoals != null ? myGoals.toString() : "null") +
                ", \"followers\": " + (followers != null ? followers.toString() : "null") +
                ", \"following\": " + (following != null ? following.toString() : "null") +
                "}";
    }
}