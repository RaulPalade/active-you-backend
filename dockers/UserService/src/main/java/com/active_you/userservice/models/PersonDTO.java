package com.active_you.userservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String sex;
    private String role;
    private Set<Workout> myWorkouts;
    private Set<Goal> myGoals;
    private List<Long> followers;
    private List<Long> following;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.email = person.getEmail();
        this.sex = person.getSex();
        this.role = person.getRole();
        this.myWorkouts = person.getMyWorkouts();
        this.myGoals = person.getMyGoals();
        this.following = person.getFollowing().stream().map(PersonFollow::getFromId).collect(Collectors.toList());
        this.followers = person.getFollowers().stream().map(PersonFollow::getToId).collect(Collectors.toList());
    }
}
