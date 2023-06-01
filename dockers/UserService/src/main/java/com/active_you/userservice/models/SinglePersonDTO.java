package com.active_you.userservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class SinglePersonDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private double height;
    private String heightUnit;
    private double weight;
    private String weightUnit;
    private String sex;
    private List<String> roles;
    private List<Long> followers;
    private List<Long> following;

    public SinglePersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.email = person.getEmail();
        this.sex = person.getSex();
        this.height = person.getHeight();
        this.heightUnit = person.getHeightUnit();
        this.weight = person.getWeight();
        this.weightUnit = person.getWeightUnit();
        this.roles = person.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        this.following = person.getFollowing().stream().map(PersonFollow::getFromId).collect(Collectors.toList());
        this.followers = person.getFollowers().stream().map(PersonFollow::getToId).collect(Collectors.toList());
    }
}
