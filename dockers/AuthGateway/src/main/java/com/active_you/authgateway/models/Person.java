package com.active_you.authgateway.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;
    private double weight;
    private String weightUnit;
    private double height;
    private String heightUnit;

    @ManyToMany
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person"), inverseJoinColumns = @JoinColumn(name = "role"))
    @OrderBy(value = "id")
    private List<Role> roles;
}
