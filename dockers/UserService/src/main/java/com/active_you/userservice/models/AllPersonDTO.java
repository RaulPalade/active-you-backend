package com.active_you.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class AllPersonDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String sex;
    private double weight;
    private String weightUnit;
    private double height;
    private String heightUnit;
    private List<String> roles;

    public AllPersonDTO(Person person) {
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
    }
}
